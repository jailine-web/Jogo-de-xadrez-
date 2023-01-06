package xadrez.pecas;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaXadrez partidaXadrez;
	
	public Peao(Tabuleiro tab, Cor cor, PartidaXadrez partidaXadrez) {
		super(tab, cor);
		this.partidaXadrez = partidaXadrez;
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
			
			//en passant
			if(pos.getLinha() == 3) {
				Posicao esquerda = new Posicao(pos.getLinha(), pos.getColuna() - 1);
				if(getTab().posicaoExistente(esquerda) && haOponeteDaPeca(esquerda) && getTab().peca(esquerda) == partidaXadrez.getEnPassantVulnerable()) {
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(pos.getLinha(), pos.getColuna() + 1);
				if(getTab().posicaoExistente(direita) && haOponeteDaPeca(direita) && getTab().peca(direita) == partidaXadrez.getEnPassantVulnerable()) {
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}
				
			}
		}
		//Peças pretas
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
			
			//en passant peças pretas
			if(pos.getLinha() == 4) {
				Posicao esquerda = new Posicao(pos.getLinha(), pos.getColuna() - 1);
				if(getTab().posicaoExistente(esquerda) && haOponeteDaPeca(esquerda) && getTab().peca(esquerda) == partidaXadrez.getEnPassantVulnerable()) {
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(pos.getLinha(), pos.getColuna() + 1);
				if(getTab().posicaoExistente(direita) && haOponeteDaPeca(direita) && getTab().peca(direita) == partidaXadrez.getEnPassantVulnerable()) {
					matriz[direita.getLinha() + 1 ][direita.getColuna()] = true;
				}
				
			}
			
		}
		
		return matriz;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
