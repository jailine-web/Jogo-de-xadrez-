package xadrez.pecas;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	private PartidaXadrez partidaXadrez;
	
	public Rei(Tabuleiro tab, Cor cor, PartidaXadrez partidaXadrez) {
		super(tab, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTab().peca(posicao);
		return p == null || p.getCor() != getCor();

	}

	private boolean testandoRoque(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTab().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContMove() == 0;
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
		
		//sw
		po.setValores(pos.getLinha()+1 , pos.getColuna()+1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
		
		//se
		po.setValores(pos.getLinha()+1 , pos.getColuna()-1);
		if (getTab().posicaoExistente(po) && podeMover(po)) {
			matriz[po.getLinha()][po.getColuna()] = true;
		}
	
		// Movimento especial de roque 
		if(getContMove() == 0 && !partidaXadrez.getVerificaCheck()) {
			//Movimento especial roque pequeno
			Posicao posT1 = new Posicao(pos.getLinha() , pos.getColuna() + 3);
			if(testandoRoque(posT1)) {
				Posicao p1 = new Posicao(pos.getLinha() , pos.getColuna() + 1);
				//casinha 2  DIREITA
				Posicao p2 = new Posicao(pos.getLinha() , pos.getColuna() + 2);
				if(getTab().peca(p1) == null && getTab().peca(p2) == null) {
					matriz [pos.getLinha()][pos.getColuna() + 2]= true;
				}
			}
			
			//Movimento especial roque grande
			Posicao posT2 = new Posicao(pos.getLinha() , pos.getColuna() - 4);
			if(testandoRoque(posT2)) {
				Posicao p1 = new Posicao(pos.getLinha() , pos.getColuna() - 1);
				//casinha 4 a esquerda
				Posicao p2 = new Posicao(pos.getLinha() , pos.getColuna() - 2);
				Posicao p3 = new Posicao(pos.getLinha() , pos.getColuna() - 3);
				if(getTab().peca(p1) == null && getTab().peca(p2) == null && getTab().peca(p3) == null) {
					matriz [pos.getLinha()][pos.getColuna() - 2]= true;
				}
			}
		}
		return matriz;
	}

}
