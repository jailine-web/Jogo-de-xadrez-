package xadrez.pecas;

import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public String toString() {
		return "R";
	}

	
}
