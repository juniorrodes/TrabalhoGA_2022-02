package Trabalho_GA;

import java.util.ArrayList;

public class Arvore_AVL {
	protected No raiz;
	protected ArrayList<Integer> arvore = new ArrayList<>();
	

	// insert
	public void insert(int chave) {
		No aux = new No(chave);
		if (raiz == null) {
			raiz = aux;
			arvore.add(chave);
		} else {
			inserirR(aux, this.raiz);
		}
		// metodo para chegar no fim?
	}

	// recursï¿½o/navegaï¿½ï¿½o
	private void inserirR(No aux, No raiz) {
		int chave = aux.getChave();
		// TODO Auto-generated method stub
		if (aux.getChave() == raiz.getChave()) {
			// chave jï¿½ existe
		} else if (aux.getChave() > raiz.getChave()) {
			if (raiz.getDireita() == null) {
				raiz.setDireita(aux);
				aux.setPai(raiz);
				fazBalanceamento(raiz);
				// arvore.add(chave);
			} else
				inserirR(aux, raiz.getDireita());
		} else if (raiz.getEsquerda() == null) {
			raiz.setEsquerda(aux);
			aux.setPai(raiz);
			fazBalanceamento(raiz);
			// arvore.add(chave);
		} else
			inserirR(aux, raiz.getEsquerda());
	}

	private void setBalanceamento(No raiz) {
		raiz.setBalanceamento(altura(raiz.getEsquerda()) - altura(raiz.getDireita()));
	}

	private int altura(No raiz) {
		if (raiz == null)
			return -1;
		if (raiz.getEsquerda() == null && raiz.getDireita() == null) {
			return 0;
		} else if (raiz.getEsquerda() == null) {
			return 1 + altura(raiz.getDireita());
		} else if (raiz.getDireita() == null) {
			return 1 + altura(raiz.getEsquerda());
		} else
			return 1 + Math.max(altura(raiz.getEsquerda()), altura(raiz.getDireita()));
	}

	// Balanceado
	public void fazBalanceamento(No raiz) {
		// TODO Auto-generated method stub
		setBalanceamento(raiz);
		int balanceamento = raiz.getBalanceamento();

		if (balanceamento == -2) {
			if (altura(raiz.getDireita().getEsquerda()) <= altura(raiz.getDireita().getDireita())) {
				raiz = rotacaoEsquerda(raiz);
			} else
				raiz = rotacaoDireitaEsquerda(raiz);
		}
		if (balanceamento == 2) {
			if (altura(raiz.getEsquerda().getEsquerda()) >= altura(raiz.getEsquerda().getDireita())) {
				raiz = rotacaoDireita(raiz);
			} else
				raiz = rotacaoEsquerdaDireita(raiz);
		}

		if (raiz.getPai() != null) {
			fazBalanceamento(raiz.getPai());
		} else
			this.raiz = raiz;
	}

	private No rotacaoEsquerda(No raiz) {
		No direita = raiz.getDireita();
		direita.setPai(raiz.getPai());
		raiz.setDireita(direita.getEsquerda());

		if (raiz.getDireita() != null) {
			raiz.getDireita().setPai(raiz);
		}

		direita.setEsquerda(raiz);
		raiz.setPai(direita);

		if (direita.getPai() != null) {
			if (direita.getPai().getDireita() == raiz) {
				direita.getPai().setDireita(direita);
			} else if (direita.getPai().getEsquerda() == raiz) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(raiz);
		setBalanceamento(direita);
		return direita;
	}

	private No rotacaoDireita(No raiz) {
		No esquerda = raiz.getEsquerda();
		esquerda.setPai(raiz.getPai());
		raiz.setEsquerda(esquerda.getDireita());

		if (raiz.getEsquerda() != null) {
			raiz.getEsquerda().setPai(raiz);
		}

		esquerda.setDireita(raiz);
		raiz.setPai(esquerda);

		if (esquerda.getPai() != null) {
			if (esquerda.getPai().getEsquerda() == raiz) {
				esquerda.getPai().setEsquerda(esquerda);
			} else if (esquerda.getPai().getDireita() == raiz) {
				esquerda.getPai().setDireita(esquerda);
			}
		}

		setBalanceamento(raiz);
		setBalanceamento(esquerda);
		return esquerda;
	}

	private No rotacaoDireitaEsquerda(No raiz) {
		raiz.setDireita(rotacaoDireita(raiz.getDireita()));
		return rotacaoEsquerda(raiz);
	}

	private No rotacaoEsquerdaDireita(No raiz) {
		raiz.setEsquerda(rotacaoEsquerda(raiz.getEsquerda()));
		return rotacaoDireita(raiz);
	}

	// remove
	public void remover(int chave) {
		removeR(this.raiz, chave);
		// System.out.println(arvore);
	}

	private No removeR(No raiz, int chave) {
		if (raiz == null)
			return raiz;
		if (raiz.getChave() > chave) {
			raiz.setEsquerda(removeR(raiz.getEsquerda(), chave));
			fazBalanceamento(raiz);
		} else if (raiz.getChave() < chave) {
			raiz.setDireita(removeR(raiz.getDireita(), chave));
			fazBalanceamento(raiz);
		} else if (raiz.getEsquerda() == null && raiz.getDireita() == null) {
			return null;
		} else if (raiz.getEsquerda() != null && raiz.getDireita() != null) {
			No maiorPai = searchHigher(raiz.getEsquerda());
			raiz.setChave(maiorPai.getChave());
			raiz.setEsquerda(removeR(raiz.getEsquerda(), maiorPai.getChave()));
			fazBalanceamento(raiz);
		} else {
			No filho = (raiz.getEsquerda() != null) ? raiz.getEsquerda() : raiz.getDireita();
			raiz = filho;
			fazBalanceamento(raiz);
		}
		return raiz;
	}

	// search
	public void procura(int chave) {
		procuraR(chave, raiz);
	}

	private void procuraR(int chave, No raiz) {
		if (raiz == null)
			System.out.println("Valor não existe na Arvore!");
		else if (chave == raiz.getChave())
			System.out.println("Valor existe na Arvore!");
		else if (chave > raiz.getChave())
			procuraR(chave, raiz.getDireita());
		else if (chave < raiz.getChave())
			procuraR(chave, raiz.getEsquerda());
	}

	private No searchHigher(No raiz) { // recebe a esquerda de quem serï¿½ excluido
		if (raiz.getDireita() == null && raiz.getEsquerda() == null)
			return raiz;
		if (raiz.getDireita() == null) {
			raiz.getEsquerda().setPai(raiz.getPai());
			raiz.getPai().setDireita(raiz.getEsquerda());
			return raiz;
		} else
			return searchHigher(raiz.getDireita());
	}

	// Pos-Ordem
	public void posOrdem() {
		posOrdem(raiz);
		System.out.println();
	}

	private void posOrdem(No raiz) {
		if (raiz != null) {
			posOrdem(raiz.getEsquerda());
			posOrdem(raiz.getDireita());
			System.out.print(raiz.getChave() + ", ");
		}
	}

	// Pre-Ordem
	public void preOrdem() {
		preOrdem(raiz);
		System.out.println();
	}

	private void preOrdem(No raiz) {
		if (raiz != null) {
			System.out.print(raiz.getChave() + ", ");
			preOrdem(raiz.getEsquerda());
			preOrdem(raiz.getDireita());
		}
	}

	// Em Ordem
	public void emOrdem() {
		emOrdem(raiz);
		System.out.println();
	}

	private void emOrdem(No raiz) {
		if (raiz != null) {
			emOrdem(raiz.getEsquerda());
			System.out.print(raiz.getChave() + ", ");
			emOrdem(raiz.getDireita());
		}
	}

	public void mostraArvore() {
		System.out.println(mostraArvoreR(this.raiz));
	}

	private String mostraArvoreR(No raiz) {
		if (raiz == null) {
			return "_";
		}
		String temp1 = mostraArvoreR(raiz.getEsquerda());
		String temp2 = mostraArvoreR(raiz.getDireita());
		if (raiz.getEsquerda() == null && raiz.getDireita() == null)
			return "_ " + raiz.getChave() + " _";
		else
			return "\t" + raiz.getChave() + "\n" + temp1 + "     " + temp2;
	}

}
