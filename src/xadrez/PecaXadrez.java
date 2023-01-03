package xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor;
	private int contMove;
	
	public PecaXadrez(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContMove() {
		return contMove;
	}
	
	public void incrementaContMover() {
		contMove ++;
	}
	
	public void decrementaContMover() {
		contMove --;
	}
	
	public PosicaoXadrez getPosicaoPeca() { 
		return PosicaoXadrez.posicaoNaMatriz(pos);
	}
	
	protected boolean haOponeteDaPeca(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTab().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}
