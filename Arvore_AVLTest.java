package Trabalho_GA;

public class Arvore_AVLTest {

	public static void main(String[] args) {
		    Arvore_AVL t = new Arvore_AVL();

		    t.insert(5);
		    t.insert(1);
		    t.insert(3);
		    t.insert(9);
		    t.insert(4);
		    t.insert(2);
		    t.remover(3);
		    t.remover(2);
		    t.mostraArvore();
		}
	}