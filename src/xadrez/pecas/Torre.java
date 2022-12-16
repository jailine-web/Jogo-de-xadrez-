package xadrez.pecas;

import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public String toString() {
		return "T";
	}
	
	

}
