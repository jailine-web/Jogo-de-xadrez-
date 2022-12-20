package xadrez;

import Tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	//O cora��o do sistema de xadrez
	
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
	private void novoLugarPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.InsercaoPeca(peca, new PosicaoXadrez(coluna, linha).ConversaoPosicoes()); 
	}
	
	private void IniciarPartida() {
		
		novoLugarPeca('c', 1, new Rei(tabuleiro, Cor.WHITE));
		novoLugarPeca('c', 2, new Rei(tabuleiro, Cor.WHITE));
		novoLugarPeca('d', 2, new Rei(tabuleiro, Cor.WHITE));
		novoLugarPeca('e', 2, new Rei(tabuleiro, Cor.WHITE));
		novoLugarPeca('e', 1, new Rei(tabuleiro, Cor.WHITE));
		novoLugarPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));
		
		novoLugarPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
		novoLugarPeca('d', 8, new Torre(tabuleiro, Cor.BLACK));
	}
}
