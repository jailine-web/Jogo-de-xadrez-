package xadrez.pecas;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Noroeste
		p.setValores(pos.getLinha() - 1, pos.getColuna()-1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1 , p.getColuna() - 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Nordeste
		p.setValores(pos.getLinha() - 1, pos.getColuna() + 1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1 , p.getColuna() + 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// Sudeste
		p.setValores(pos.getLinha() + 1, pos.getColuna() + 1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// sudoeste
		p.setValores(pos.getLinha() + 1, pos.getColuna()-1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna()-1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}

	@Override
	public String toString() {
		return "B";
	}

}
