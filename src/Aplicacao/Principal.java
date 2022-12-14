package Aplicacao;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.PartidaXadrez;

public class Principal {

	public static void main(String[] args) {

		PartidaXadrez px = new PartidaXadrez();
		UI.printTabuleiro(px.getPecas());
		Tabuleiro tabuleiro = new Tabuleiro(8, 8);
		
	}

}
