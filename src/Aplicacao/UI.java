package Aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	// Cores do console cores do texto

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// Cores do fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static PosicaoXadrez lerPosicaoxadrez(Scanner ler) {
		try {

			String s = ler.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro na cria��o da posi��o:");
		}
	}
	
	public static void printPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturada) {
		printTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		printPecasCapturadas(capturada);
		System.out.println();
		System.out.println("Partida: "+ partidaXadrez.getDirecao());
		
		if(!partidaXadrez.getXequeMate()) {
			System.out.println("Esperand o jogador: "+ partidaXadrez.getJogadorAtual());
			if(partidaXadrez.getVerificaCheck()) {
				System.out.println("CHECK!");
			}
		}
		else {
			System.out.println("XEQUEMATE!");
			System.out.println("Vencedor: "+ partidaXadrez.getJogadorAtual());
		}
	}

	public static void printTabuleiro(PecaXadrez[][] pecas) {

		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h ");
	}

	public static void printTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis) {

		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + "  ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h ");
	}
	
	private static void printPeca(PecaXadrez peca, boolean corFundo) {
		if(corFundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-"+ ANSI_RESET);
		} else {
			if (peca.getCor() == Cor.WHITE) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	private static void printPecasCapturadas(List<PecaXadrez> pecasCapturadas) {
		List<PecaXadrez> branca = pecasCapturadas.stream().filter(x -> x.getCor() == Cor.WHITE).collect(Collectors.toList()); 
		List<PecaXadrez> preta = pecasCapturadas.stream().filter(x -> x.getCor() == Cor.BLACK).collect(Collectors.toList()); 
		System.out.println("Pecas capturadas: ");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		//Modo de imprimir uma lista de arrays no java
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Pretas:  ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.print(ANSI_RESET);
		
	}
}
