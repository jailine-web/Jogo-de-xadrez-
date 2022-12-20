package Tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroExcecao("Erro ao criar tabuleiro (linhas e colunas menores que 1) ");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		if (!posicaoExistente(linha, coluna)) {
			throw new TabuleiroExcecao("A posição não existe no tabuleiro!");
		}
		return pecas [linha][coluna];
	}
	
	public Peca peca(Posicao pos) {
		if (!posicaoExistente(pos)) {
			throw new TabuleiroExcecao("Posicao não encontrada no tabuleiro!");
		}
		return pecas[pos.getLinha()][pos.getColuna()];
	}
	
	public void InsercaoPeca(Peca peca, Posicao posicao) {
		if (temPecas(posicao)) {
			throw new TabuleiroExcecao("Já existe uma peça nesta posição! " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.pos = posicao;
	}
	
	//Método auxiliar
	private boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < getLinhas() && coluna >= 0 && coluna < getColunas();
	}
	
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean temPecas(Posicao pos) {
		if (!posicaoExistente(pos)) {
			throw new TabuleiroExcecao("Posicao não encontrada no tabuleiro!");
		}
		return peca(pos) != null;
	}
}
