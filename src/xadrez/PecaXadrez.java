package xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor;

	public PecaXadrez(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	
}
