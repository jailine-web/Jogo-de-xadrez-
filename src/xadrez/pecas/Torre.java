package xadrez.pecas;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTab().getLinhas()][getTab().getColunas()];
		
		Posicao p = new Posicao(0, 0);

		// Marca de verdadeiro as posi��es acima da pe�a
		p.setValores(pos.getLinha() - 1, pos.getColuna());
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Marca de verdadeiro as posi��es a esquerda da pe�a
		p.setValores(pos.getLinha(), pos.getColuna() - 1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// Marca de verdadeiro as posi��es a direita da pe�a
		p.setValores(pos.getLinha(), pos.getColuna() + 1);
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Marca de verdadeiro as posi��es baixo da pe�a
		p.setValores(pos.getLinha() + 1, pos.getColuna());
		while (getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}

}
