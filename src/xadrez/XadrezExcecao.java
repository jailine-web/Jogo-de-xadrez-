package xadrez;

public class XadrezExcecao extends RuntimeException{
	//N�mero de serializa��o
	private static final long serialVersionUID = 1L;

	public XadrezExcecao(String msg) {
		//Repassa para o construtor da super classe essa mensagem
		super(msg);
	}
}
