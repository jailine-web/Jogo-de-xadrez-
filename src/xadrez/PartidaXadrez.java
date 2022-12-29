package xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Tabuleiro.TabuleiroExcecao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	//O coração do sistema de xadrez
	
	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		IniciarPartida();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i< tabuleiro.getLinhas(); i ++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez)tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public boolean [][] movimentosPossiveis(PosicaoXadrez PosicaoOrigem){
		Posicao posicao = PosicaoOrigem.ConversaoPosicoes();//toPosition()
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public PecaXadrez movendoPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino){
		Posicao origem = posicaoOrigem.ConversaoPosicoes();
		Posicao destino = posicaoDestino.ConversaoPosicoes();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerMover(origem, destino);
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca pecaCapturada = tabuleiro.removePeca(destino);
		tabuleiro.InsercaoPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Posicao posic) {
		if(!tabuleiro.temPecas(posic)) {
			throw new TabuleiroExcecao("Não há peca na posição de origem! ");
		}
		if(!tabuleiro.peca(posic).haMovimentosPossiveis()) {
			throw new TabuleiroExcecao("Não existe movimentos possíveis para a peça escolhida");
					
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino){
		if(!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new TabuleiroExcecao("A peça escolhida na origem não pode ser movida para a posição de destino!");
		}
	}
	
	private void novoLugarPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.InsercaoPeca(peca, new PosicaoXadrez(coluna, linha).ConversaoPosicoes()); 
	}
	
	private void IniciarPartida() {
		
		novoLugarPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
		novoLugarPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
		novoLugarPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
		novoLugarPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
		novoLugarPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
		novoLugarPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));
		
		novoLugarPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('d', 8, new Rei(tabuleiro, Cor.BLACK));
	}
}
