package xadrez.pecas;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Acima
		p.setValores(pos.getLinha() - 1, pos.getColuna());
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Esquerda
		p.setValores(pos.getLinha(), pos.getColuna() - 1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// Direita
		p.setValores(pos.getLinha(), pos.getColuna() + 1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Abaixo
		p.setValores(pos.getLinha() + 1, pos.getColuna());
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Noroeste
		p.setValores(pos.getLinha() - 1, pos.getColuna() - 1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Nordeste
		p.setValores(pos.getLinha() - 1, pos.getColuna() + 1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
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
		p.setValores(pos.getLinha() + 1, pos.getColuna() - 1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}

}
