package Tabuleiro;

public abstract class Peca {
	
	protected Posicao pos;
	private Tabuleiro tab;
	
	public Peca(Tabuleiro tab) {
		this.tab = tab;
		pos = null;
	}

	protected Tabuleiro getTab() {
		return tab;
	}

	public abstract boolean[][] movimentosPossiveis();

	public boolean movimentoPossivel(Posicao posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean haMovimentosPossiveis() {
		boolean[][] mat = movimentosPossiveis();
		for(int i =0; i<mat.length; i++) {
			for(int j = 0; j< mat.length; j++) {
				//Verificando a existencia de movimentos possíveis
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
}
