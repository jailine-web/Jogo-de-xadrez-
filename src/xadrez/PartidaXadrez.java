package xadrez;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Tabuleiro.TabuleiroExcecao;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	// O coração do sistema de xadrez

	private int direcao;
	private Tabuleiro tabuleiro;
	private Cor jogadorAtual;
	private boolean verifica; // representa a propriedade em check
	private boolean xequeMate;
	private PecaXadrez enPassantVulnerable;
	private PecaXadrez promocao;

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

	public PecaXadrez getEnPassantVulnerable() {
		return enPassantVulnerable;
	}
	
	public PecaXadrez getPromocao() {
		return promocao;
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

		PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.peca(destino);

		//promoçao
		promocao = null;
		
		if(pecaMovida instanceof Peao) {
			if(pecaMovida.getCor() == Cor.WHITE && destino.getLinha() == 0 || pecaMovida.getCor() == Cor.BLACK && destino.getLinha() == 7) {
				promocao = (PecaXadrez)tabuleiro.peca(destino);
				promocao = substituirPecaPromovida("Q");

			}
		}
		
		verifica = (verificandoCheck(oponente(jogadorAtual))) ? true : false;

		if (validandoXequeMate(oponente(jogadorAtual))) {
			xequeMate = true;
		} else {
			proximaDirecao();
		}
		// en passant movimento especial
		if (pecaMovida instanceof Peao
				&& (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			enPassantVulnerable = pecaMovida;
		} 
		else {
			enPassantVulnerable = null;
		}

		return (PecaXadrez) pecaCapturada;
	}

	public PecaXadrez substituirPecaPromovida(String tipoPeca) {
		
		if(promocao == null) {
			throw new IllegalStateException("Não há peça a ser promovida");
		}
		
		if(!tipoPeca.equals("B") && !tipoPeca.equals("C") && !tipoPeca.equals("T")&& !tipoPeca.equals("Q")) {
			throw new InvalidParameterException("Tipo inválido para promocao");
		}
		
		Posicao pos = promocao.getPosicaoPeca().ConversaoPosicoes();
		Peca p = tabuleiro.removePeca(pos);
		pecasNoTabuleiro.remove(p);
		
		PecaXadrez pecaNova = pecaNova(tipoPeca, promocao.getCor());
		tabuleiro.InsercaoPeca(pecaNova, pos);
		pecasNoTabuleiro.add(pecaNova);
		
		return pecaNova;
	}
	
	private PecaXadrez pecaNova(String tipo, Cor cor) {
		
		if(tipo.equals("B")) return new Bispo (tabuleiro, cor);
		if(tipo.equals("C")) return new Cavalo(tabuleiro, cor);
		if(tipo.equals("Q")) return new Rainha(tabuleiro, cor);
		return new Torre(tabuleiro, cor);
	}
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(origem);
		p.incrementaContMover();
		Peca pecaCapturada = tabuleiro.removePeca(destino);
		tabuleiro.InsercaoPeca(p, destino);
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		// Tratamento do movimento especial roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemT);
			tabuleiro.InsercaoPeca(torre, destinoT);
			torre.incrementaContMover();
		}

		// Tratamento do movimento especial roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemT);
			tabuleiro.InsercaoPeca(torre, destinoT);
			torre.incrementaContMover();
		}

		// Movimento especial en passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if (p.getCor() == Cor.WHITE) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				}

				else {
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removePeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}

		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(destino);
		p.decrementaContMover();
		tabuleiro.InsercaoPeca(p, origem);

		if (pecaCapturada != null) {
			tabuleiro.InsercaoPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

		// Tratamento do movimento especial roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoT);
			tabuleiro.InsercaoPeca(torre, origemT);
			torre.decrementaContMover();
		}

		// Tratamento do movimento especial roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoT);
			tabuleiro.InsercaoPeca(torre, origemT);
			torre.decrementaContMover();
		}

		// Movimento especial en passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				PecaXadrez peao = (PecaXadrez)tabuleiro.removePeca(destino);
				
				Posicao posicaoPeao;
				if (p.getCor() == Cor.WHITE) {
					posicaoPeao = new Posicao(3 , destino.getColuna());
				}

				else {
					posicaoPeao = new Posicao(4 , destino.getColuna());
				}
				tabuleiro.InsercaoPeca(peao, posicaoPeao);
				
				pecaCapturada = tabuleiro.removePeca(posicaoPeao);
			}
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

		novoLugarPeca('a', 1, new Torre(tabuleiro,  Cor.WHITE));
		novoLugarPeca('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
		novoLugarPeca('c', 1, new Bispo(tabuleiro,  Cor.WHITE));
		novoLugarPeca('d', 1, new Rainha(tabuleiro, Cor.WHITE));
		novoLugarPeca('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
		novoLugarPeca('f', 1, new Bispo(tabuleiro,  Cor.WHITE));
		novoLugarPeca('h', 1, new Torre(tabuleiro,  Cor.WHITE));
		novoLugarPeca('e', 1, new Rei(tabuleiro, Cor.WHITE, this));
		novoLugarPeca('a', 2, new Peao(tabuleiro, Cor.WHITE, this));
		novoLugarPeca('b', 2, new Peao(tabuleiro, Cor.WHITE, this));
		novoLugarPeca('c', 2, new Peao(tabuleiro, Cor.WHITE, this));
		novoLugarPeca('d', 2, new Peao(tabuleiro, Cor.WHITE, this));
		novoLugarPeca('e', 2, new Peao(tabuleiro, Cor.WHITE, this));
		novoLugarPeca('f', 2, new Peao(tabuleiro, Cor.WHITE, this));
		novoLugarPeca('g', 2, new Peao(tabuleiro, Cor.WHITE, this));
		novoLugarPeca('h', 2, new Peao(tabuleiro, Cor.WHITE, this));

		novoLugarPeca('a', 8, new Torre(tabuleiro,  Cor.BLACK));
		novoLugarPeca('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
		novoLugarPeca('c', 8, new Bispo(tabuleiro,  Cor.BLACK));
		novoLugarPeca('d', 8, new Rainha(tabuleiro, Cor.BLACK));
		novoLugarPeca('f', 8, new Bispo(tabuleiro,  Cor.BLACK));
		novoLugarPeca('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
		novoLugarPeca('h', 8, new Torre(tabuleiro,  Cor.BLACK));
		novoLugarPeca('e', 8, new Rei(tabuleiro, Cor.BLACK, this));
		novoLugarPeca('a', 7, new Peao(tabuleiro, Cor.BLACK, this));
		novoLugarPeca('b', 7, new Peao(tabuleiro, Cor.BLACK, this));
		novoLugarPeca('c', 7, new Peao(tabuleiro, Cor.BLACK, this));
		novoLugarPeca('d', 7, new Peao(tabuleiro, Cor.BLACK, this));
		novoLugarPeca('e', 7, new Peao(tabuleiro, Cor.BLACK, this));
		novoLugarPeca('f', 7, new Peao(tabuleiro, Cor.BLACK, this));
		novoLugarPeca('g', 7, new Peao(tabuleiro, Cor.BLACK, this));
		novoLugarPeca('h', 7, new Peao(tabuleiro, Cor.BLACK, this));

	}
}
