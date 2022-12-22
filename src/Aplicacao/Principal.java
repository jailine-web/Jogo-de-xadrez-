package Aplicacao;

import java.util.Scanner;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Principal {

	public static void main(String[] args) {

		Scanner ler = new Scanner(System.in);
		
		PartidaXadrez px = new PartidaXadrez();
		
		while(true) {
			UI.printTabuleiro(px.getPecas());
			System.out.println();
			System.out.println("Origem");
			PosicaoXadrez origem = UI.lerPosicaoxadrez(ler);
			
			System.out.println();
			System.out.println("Destino");
			PosicaoXadrez destino = UI.lerPosicaoxadrez(ler); 
			
			PecaXadrez pecaCapturada = px.movendoPeca(origem, destino);
		}
		
	}

}
