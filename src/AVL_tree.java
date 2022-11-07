import java.util.ArrayList;

public class AVL_tree {
	protected Node root;
	protected ArrayList<Integer> key = new ArrayList<>();
	

	// insert
	public void insert(int key) {
		Node aux = new Node(key);
		if (root == null) {
			root = aux;
			this.key.add(key);
		} else {
			inserirR(aux, this.root);
		}
		// metodo para chegar no fim?
	}

	// recursï¿½o/navegaï¿½ï¿½o
	private void inserirR(Node aux, Node raiz) {
		int chave = aux.getKey();
		if (aux.getKey() == raiz.getKey()) {
			// chave jï¿½ existe
		} else if (aux.getKey() > raiz.getKey()) {
			if (raiz.getRightChild() == null) {
				raiz.setRightChild(aux);
				aux.setPai(raiz);
				fazBalanceamento(raiz);
			} else
				inserirR(aux, raiz.getRightChild());
		} else if (raiz.getLeftChild() == null) {
			raiz.setLeftChild(aux);
			aux.setPai(raiz);
			fazBalanceamento(raiz);
		} else
			inserirR(aux, raiz.getLeftChild());
	}

	private void setBalanceamento(Node raiz) {
		raiz.setBalanceFactor(altura(raiz.getLeftChild()) - altura(raiz.getRightChild()));
	}

	private int altura(Node raiz) {
		if (raiz == null)
			return -1;
		if (raiz.getLeftChild() == null && raiz.getRightChild() == null) {
			return 0;
		} else if (raiz.getLeftChild() == null) {
			return 1 + altura(raiz.getRightChild());
		} else if (raiz.getRightChild() == null) {
			return 1 + altura(raiz.getLeftChild());
		} else
			return 1 + Math.max(altura(raiz.getLeftChild()), altura(raiz.getRightChild()));
	}

	// Balanceado
	public void fazBalanceamento(Node raiz) {
		// TODO Auto-generated method stub
		setBalanceamento(raiz);
		int balanceamento = raiz.getBalanceFactor();

		if (balanceamento == -2) {
			if (altura(raiz.getRightChild().getLeftChild()) <= altura(raiz.getRightChild().getRightChild())) {
				raiz = rotacaoEsquerda(raiz);
			} else
				raiz = rotacaoDireitaEsquerda(raiz);
		}
		if (balanceamento == 2) {
			if (altura(raiz.getLeftChild().getLeftChild()) >= altura(raiz.getLeftChild().getRightChild())) {
				raiz = rotacaoDireita(raiz);
			} else
				raiz = rotacaoEsquerdaDireita(raiz);
		}

		if (raiz.getPai() != null) {
			fazBalanceamento(raiz.getPai());
		} else
			this.root = raiz;
	}

	private Node rotacaoEsquerda(Node raiz) {
		Node direita = raiz.getRightChild();
		direita.setPai(raiz.getPai());
		raiz.setRightChild(direita.getLeftChild());

		if (raiz.getRightChild() != null) {
			raiz.getRightChild().setPai(raiz);
		}

		direita.setLeftChild(raiz);
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

	private Node rotacaoDireita(Node raiz) {
		Node esquerda = raiz.getLeftChild();
		esquerda.setPai(raiz.getPai());
		raiz.setLeftChild(esquerda.getRightChild());

		if (raiz.getLeftChild() != null) {
			raiz.getLeftChild().setPai(raiz);
		}

		esquerda.setRightChild(raiz);
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

	private Node rotacaoDireitaEsquerda(Node raiz) {
		raiz.setRightChild(rotacaoDireita(raiz.getRightChild()));
		return rotacaoEsquerda(raiz);
	}

	private Node rotacaoEsquerdaDireita(Node raiz) {
		raiz.setLeftChild(rotacaoEsquerda(raiz.getLeftChild()));
		return rotacaoDireita(raiz);
	}

	// remove
	public void remover(int chave) {
		removeR(this.root, chave);
		// System.out.println(arvore);
	}

	private Node removeR(Node raiz, int chave) {
		if (raiz == null)
			return raiz;
		if (raiz.getKey() > chave) {
			raiz.setLeftChild(removeR(raiz.getLeftChild(), chave));
			fazBalanceamento(raiz);
		} else if (raiz.getKey() < chave) {
			raiz.setRightChild(removeR(raiz.getRightChild(), chave));
			fazBalanceamento(raiz);
		} else if (raiz.getLeftChild() == null && raiz.getRightChild() == null) {
			return null;
		} else if (raiz.getLeftChild() != null && raiz.getRightChild() != null) {
			Node maiorPai = searchHigher(raiz.getLeftChild());
			raiz.setKey(maiorPai.getKey());
			raiz.setLeftChild(removeR(raiz.getLeftChild(), maiorPai.getKey()));
			fazBalanceamento(raiz);
		} else {
			Node filho = (raiz.getLeftChild() != null) ? raiz.getLeftChild() : raiz.getRightChild();
			raiz = filho;
			fazBalanceamento(raiz);
		}
		return raiz;
	}

	// search
	public void procura(int chave) {
		procuraR(chave, root);
	}

	private void procuraR(int chave, Node raiz) {
		if (raiz == null)
			System.out.println("Valor não existe na Arvore!");
		else if (chave == raiz.getKey())
			System.out.println("Valor existe na Arvore!");
		else if (chave > raiz.getKey())
			procuraR(chave, raiz.getRightChild());
		else if (chave < raiz.getKey())
			procuraR(chave, raiz.getLeftChild());
	}

	private Node searchHigher(Node raiz) { // recebe a esquerda de quem serï¿½ excluido
		if (raiz.getRightChild() == null && raiz.getLeftChild() == null)
			return raiz;
		if (raiz.getRightChild() == null) {
			raiz.getLeftChild().setPai(raiz.getPai());
			raiz.getPai().setDireita(raiz.getLeftChild());
			return raiz;
		} else
			return searchHigher(raiz.getRightChild());
	}

	// Pos-Ordem
	public void posOrdem() {
		posOrdem(root);
		System.out.println();
	}

	private void posOrdem(Node raiz) {
		if (raiz != null) {
			posOrdem(raiz.getLeftChild());
			posOrdem(raiz.getRightChild());
			System.out.print(raiz.getKey() + ", ");
		}
	}

	// Pre-Ordem
	public void preOrdem() {
		preOrdem(root);
		System.out.println();
	}

	private void preOrdem(Node raiz) {
		if (raiz != null) {
			System.out.print(raiz.getKey() + ", ");
			preOrdem(raiz.getLeftChild());
			preOrdem(raiz.getRightChild());
		}
	}

	// Em Ordem
	public void emOrdem() {
		emOrdem(root);
		System.out.println();
	}

	private void emOrdem(Node raiz) {
		if (raiz != null) {
			emOrdem(raiz.getLeftChild());
			System.out.print(raiz.getKey() + ", ");
			emOrdem(raiz.getRightChild());
		}
	}

	public void mostraArvore() {
		System.out.println(mostraArvoreR(this.root));
	}

	private String mostraArvoreR(Node raiz) {
		if (raiz == null) {
			return "_";
		}
		String temp1 = mostraArvoreR(raiz.getLeftChild());
		String temp2 = mostraArvoreR(raiz.getRightChild());
		if (raiz.getLeftChild() == null && raiz.getRightChild() == null)
			return "_ " + raiz.getKey() + " _";
		else
			return "\t" + raiz.getKey() + "\n" + temp1 + "     " + temp2;
	}

}
