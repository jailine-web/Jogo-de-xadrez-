package xadrez;

public class XadrezExcecao extends RuntimeException{
	//Número de serialização
	private static final long serialVersionUID = 1L;

	public XadrezExcecao(String msg) {
		//Repassa para o construtor da super classe essa mensagem
		super(msg);
	}
}
