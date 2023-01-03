package xadrez;

import Tabuleiro.Posicao;

public class PosicaoXadrez {

	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if (coluna< 'a' || coluna > 'h' || linha < 1 || coluna < 1) {
			throw new XadrezExcecao ("Erro ao iniciar a posição as posições vão de a1 a h8!! ");
		}
		
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	//o número corresponde sempre a 8 - a linha do xadrez coluna subtraído do carctere
	protected Posicao ConversaoPosicoes() {
		return new Posicao(8- linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez posicaoNaMatriz(Posicao posicao) {
		return new PosicaoXadrez((char) ((char)'a' + posicao.getColuna()) , 8 - posicao.getLinha());				
				
	}

	@Override
	public String toString() {
		return " " + coluna  + linha;
	}
	
	
}
