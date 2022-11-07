import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AVLTest {

	private static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
//		Node t = new Node(readInt("Digite o valor do primeiro nó da árvore:"));
//
//		int value = readInt("Digite valor do nó:");
//
//		while (value != 0) {
//			t.insert(value);
//			value = readInt("Digite valor do nó:");
//		}
		Node t = new Node(8);

		t.insert(10);
		t.insert(49);
		t.insert(5);
		t.insert(6);
		t.insert(7);
		t.insert(15);
		t.insert(24);
		t.insert(13);
		t.insert(35);
		t.insert(46);


		List<Integer> list = t.inOrder();
		for (Integer i : list) {
			System.out.printf("%d ", i);
		}

		System.out.println();

		list = t.preOrder();
		for (Integer i : list) {
			System.out.printf("%d ", i);
		}

		System.out.println();

		list = t.postOrder();
		for (Integer i : list) {
			System.out.printf("%d ", i);
		}

		System.out.println();
		System.out.println();
		System.out.println();

		t.delete(8);

		list = t.inOrder();
		for (Integer i : list) {
			System.out.printf("%d ", i);
		}

		System.out.println();

		list = t.preOrder();
		for (Integer i : list) {
			System.out.printf("%d ", i);
		}

		System.out.println();

		list = t.postOrder();
		for (Integer i : list) {
			System.out.printf("%d ", i);
		}

//		t.mostraArvore();
	}

	private static int readInt(String msg) {
		System.out.println(msg);
		return reader.nextInt();
	}
}