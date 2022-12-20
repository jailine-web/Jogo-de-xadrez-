package xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
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
	
	private void IniciarPartida() {
		tabuleiro.InsercaoPeca(new Torre(tabuleiro, Cor.WHITE), new Posicao(0, 1));
		tabuleiro.InsercaoPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(6, 3));
		tabuleiro.InsercaoPeca(new Rei(tabuleiro, Cor.WHITE), new Posicao(7, 3));
		
	}
}
