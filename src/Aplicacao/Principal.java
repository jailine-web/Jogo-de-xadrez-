package Aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Tabuleiro.TabuleiroExcecao;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Principal {

	public static void main(String[] args) {

		Scanner ler = new Scanner(System.in);
		PartidaXadrez px = new PartidaXadrez();
		List<PecaXadrez> pecasCapturadas = new ArrayList<PecaXadrez>();
		
		
		while(true) {
			try {
				
				UI.limparTela();
				UI.printPartida(px, pecasCapturadas);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoxadrez(ler);
				
				boolean[][] movimentosPossiveis = px.movimentosPossiveis(origem);
				UI.limparTela();
				UI.printTabuleiro(px.getPecas(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicaoxadrez(ler); 
				
				PecaXadrez pecaCapturada = px.movendoPeca(origem, destino);
				
				if(pecaCapturada != null) {
					pecasCapturadas.add(pecaCapturada);
				}
			}
			catch(TabuleiroExcecao e) {
				System.out.println(e.getMessage());
				System.out.println("Digite enter para inserir uma nova posi��o");
				ler.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.println("Digite enter para inserir uma nova posi��o");
				ler.nextLine();
			}
		}
		
	}

}
