package xadrez.pecas;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	public Peao(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);
		if(getCor() == Cor.WHITE) {
			
			p.setValores(pos.getLinha() - 1, pos.getColuna());
			//Primeiro movimento possível
			if(getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;	
			}
			
			p.setValores(pos.getLinha() - 2, pos.getColuna());
			Posicao p2 = new Posicao(pos.getLinha() -1 , pos.getLinha());
			if(getTab().posicaoExistente(p) && !getTab().temPecas(p) && 
			   getTab().posicaoExistente(p2) && !getTab().temPecas(p2) && getContMove() == 0) {
			   matriz[p.getLinha()][p.getColuna()] = true;	
			}
			
			p.setValores(pos.getLinha() - 1, pos.getColuna() - 1);
			if(getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;	
			}
			
			p.setValores(pos.getLinha() - 1, pos.getColuna() + 1);
			if(getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;	
			}
		}
		else {
			p.setValores(pos.getLinha() + 1, pos.getColuna());
			//Primeiro movimento possível
			if(getTab().posicaoExistente(p) && !getTab().temPecas(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;	
			}
			
			p.setValores(pos.getLinha() + 2, pos.getColuna());
			Posicao p2 = new Posicao(pos.getLinha() + 1 , pos.getLinha());
			if(getTab().posicaoExistente(p) && !getTab().temPecas(p) && 
			   getTab().posicaoExistente(p2) && !getTab().temPecas(p2) && getContMove() == 0) {
			   matriz[p.getLinha()][p.getColuna()] = true;	
			}
			
			p.setValores(pos.getLinha() + 1, pos.getColuna() - 1);
			if(getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;	
			}
			
			p.setValores(pos.getLinha() + 1, pos.getColuna() + 1);
			if(getTab().posicaoExistente(p) && haOponeteDaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;	
			}
		}
		
		return matriz;
	}
	
	@Override
	public String toString() {
		return "p";
	}

}
