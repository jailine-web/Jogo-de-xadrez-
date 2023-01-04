package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Tabuleiro.TabuleiroExcecao;
import xadrez.pecas.Bispo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	// O coração do sistema de xadrez

	private int direcao;
	private Tabuleiro tabuleiro;
	private Cor jogadorAtual;
	private boolean verifica; // representa a propriedade em check
	private boolean xequeMate;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		direcao = 1;
		jogadorAtual = Cor.WHITE;
		IniciarPartida();
	}

	public int getDirecao() {
		return direcao;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getVerificaCheck() {
		return verifica;
	}

	public boolean getXequeMate() {
		return xequeMate;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez PosicaoOrigem) {
		Posicao posicao = PosicaoOrigem.ConversaoPosicoes();// toPosition()
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaXadrez movendoPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.ConversaoPosicoes();
		Posicao destino = posicaoDestino.ConversaoPosicoes();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerMover(origem, destino);
		if (verificandoCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new TabuleiroExcecao("Voce nao pode se colocar em check ");
		}

		verifica = (verificandoCheck(oponente(jogadorAtual))) ? true : false;
		
		if(validandoXequeMate(oponente(jogadorAtual))) {
			xequeMate = true;
		}
		else {
			proximaDirecao();
			
		}
		return (PecaXadrez) pecaCapturada;
	}

	private Peca fazerMover(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(origem);
		p.incrementaContMover();
		Peca pecaCapturada = tabuleiro.removePeca(destino);
		tabuleiro.InsercaoPeca(p, destino);
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}

	/**/
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez  p = (PecaXadrez)tabuleiro.removePeca(destino);
		p.decrementaContMover();
		tabuleiro.InsercaoPeca(p, origem);

		if (pecaCapturada != null) {
			tabuleiro.InsercaoPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

	}

	private void validarPosicaoOrigem(Posicao posic) {
		if (!tabuleiro.temPecas(posic)) {
			throw new TabuleiroExcecao("Não há peca na posição de origem! ");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posic)).getCor()) {
			throw new TabuleiroExcecao("A peça escolhida não é sua");
		}
		if (!tabuleiro.peca(posic).haMovimentosPossiveis()) {
			throw new TabuleiroExcecao("Não existe movimentos possíveis para a peça escolhida");

		}
	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new TabuleiroExcecao("A peça escolhida na origem não pode ser movida para a posição de destino!");
		}
	}

	private void proximaDirecao() {
		direcao++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private PecaXadrez rei(Cor cor) {
		// Forma padrão de se filtrar uma lista(Expressão lambida)
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor " + cor + " no tabuleiro");
	}

	private boolean verificandoCheck(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoPeca().ConversaoPosicoes();
		List<Peca> pecasOponentes = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecasOponentes) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean validandoXequeMate(Cor cor) {
		if (!verificandoCheck(cor)) {
			return false;
		}
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : lista) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaXadrez) p).getPosicaoPeca().ConversaoPosicoes();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = fazerMover(origem, destino);
						boolean testexeque = verificandoCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testexeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void novoLugarPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.InsercaoPeca(peca, new PosicaoXadrez(coluna, linha).ConversaoPosicoes());
		pecasNoTabuleiro.add(peca);
	}

	private void IniciarPartida() {

		novoLugarPeca('a', 1, new Torre(tabuleiro, Cor.WHITE));
		novoLugarPeca('c', 1, new Bispo(tabuleiro, Cor.WHITE));
		novoLugarPeca('e', 1, new Rei(tabuleiro, Cor.WHITE));
		novoLugarPeca('f', 1, new Bispo(tabuleiro, Cor.WHITE));
		novoLugarPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
		novoLugarPeca('a', 2, new Peao(tabuleiro, Cor.WHITE));
		novoLugarPeca('b', 2, new Peao(tabuleiro, Cor.WHITE));
		novoLugarPeca('c', 2, new Peao(tabuleiro, Cor.WHITE)); 
		novoLugarPeca('d', 2, new Peao(tabuleiro, Cor.WHITE)); 
		novoLugarPeca('e', 2, new Peao(tabuleiro, Cor.WHITE));
		novoLugarPeca('f', 2, new Peao(tabuleiro, Cor.WHITE));
		novoLugarPeca('g', 2, new Peao(tabuleiro, Cor.WHITE)); 
		novoLugarPeca('h', 2, new Peao(tabuleiro, Cor.WHITE)); 
		 
		novoLugarPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('c', 8, new Bispo(tabuleiro, Cor.BLACK));
		novoLugarPeca('e', 8, new Rei(tabuleiro, Cor.BLACK));
		novoLugarPeca('f', 8, new Bispo(tabuleiro, Cor.BLACK));
		novoLugarPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('a', 7, new Peao(tabuleiro, Cor.BLACK));
		novoLugarPeca('b', 7, new Peao(tabuleiro, Cor.BLACK));
		novoLugarPeca('c', 7, new Peao(tabuleiro, Cor.BLACK)); 
		novoLugarPeca('d', 7, new Peao(tabuleiro, Cor.BLACK)); 
		novoLugarPeca('e', 7, new Peao(tabuleiro, Cor.BLACK));
		novoLugarPeca('f', 7, new Peao(tabuleiro, Cor.BLACK));
		novoLugarPeca('g', 7, new Peao(tabuleiro, Cor.BLACK)); 
		novoLugarPeca('h', 7, new Peao(tabuleiro, Cor.BLACK)); 
		
	}
}
