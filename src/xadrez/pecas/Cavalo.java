package xadrez.pecas;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{
	public Cavalo(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTab().peca(posicao);
		return p == null || p.getCor() != getCor();

	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTab().getLinhas()][getTab().getColunas()];
		Posicao po = new Posicao(0, 0);

		
		po.setValores(pos.getLinha() - 1, pos.getColuna() - 2);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}

		po.setValores(pos.getLinha() - 2, pos.getColuna() - 1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		
		po.setValores(pos.getLinha() - 2, pos.getColuna() + 1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		po.setValores(pos.getLinha() - 1, pos.getColuna() +2);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		po.setValores(pos.getLinha() + 1 , pos.getColuna() + 2);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		po.setValores(pos.getLinha() + 2 , pos.getColuna() +1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		po.setValores(pos.getLinha() +2 , pos.getColuna() - 1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		po.setValores(pos.getLinha() + 1 , pos.getColuna()- 2);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		

		return matriz;
	}


}
