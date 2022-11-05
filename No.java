package Trabalho_GA;

//Pra que serve
//Tantos C�digos?
//Se a vida
//n�o � programada
//e as melhores coisas
//n�o tem l�gica

public class No {
	private No pai;
	private No esquerda;
	private No direita;
	private int balanceamento;
	private int chave;

	public No(int chave) {
		setEsquerda(setDireita(setPai(null)));
		setChave(chave);
		setBalanceamento(0);
	}

	public String toString() {
		return Integer.toString(getChave());
	}

	public No getPai() {
		return pai;
	}

	public No setPai(No pai) {
		this.pai = pai;
		return pai;
	}

	public No getEsquerda() {
		return esquerda;
	}

	public No setEsquerda(No esquerda) {
		this.esquerda = esquerda;
		return esquerda;
	}

	public No getDireita() {
		return direita;
	}

	public No setDireita(No direita) {
		this.direita = direita;
		return direita;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}
}
