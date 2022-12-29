package xadrez.pecas;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTab().peca(posicao);
		return p == null || p.getCor() != getCor();

	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTab().getLinhas()][getTab().getColunas()];
		Posicao po = new Posicao(0, 0);

		// acima
		po.setValores(pos.getLinha() - 1, pos.getColuna());
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}

		// abaixo
		po.setValores(pos.getLinha() + 1, pos.getColuna());
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		//Esquerda
		po.setValores(pos.getLinha(), pos.getColuna() - 1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		//Direita
		po.setValores(pos.getLinha(), pos.getColuna()+1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		//Noroeste
		po.setValores(pos.getLinha()-1 , pos.getColuna()-1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		//Nordeste
		po.setValores(pos.getLinha()-1 , pos.getColuna()+1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		//suldoeste
		po.setValores(pos.getLinha()+1 , pos.getColuna()-1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		//suldeste
		po.setValores(pos.getLinha()+1 , pos.getColuna()+1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		//suldoeste
		po.setValores(pos.getLinha()+1 , pos.getColuna()-1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		

		return matriz;
	}

}
