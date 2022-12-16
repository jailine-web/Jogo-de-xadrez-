package Tabuleiro;

public class Peca {
	
	protected Posicao pos;
	private Tabuleiro tab;
	
	public Peca(Tabuleiro tab) {
		this.tab = tab;
		pos = null;
	}

	protected Tabuleiro getTab() {
		return tab;
	}

	
	
	
}
