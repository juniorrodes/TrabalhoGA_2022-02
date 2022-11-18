import java.util.List;
import java.util.Scanner;

public class AVLTest {

	private static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		Node<String> t = new Node<String>("Salve");

		t.insert("José");
		t.insert("Carlos");
		t.insert("Marcos");
		t.insert("Helena");
		t.insert("Siegfried");
		System.out.println(t);
//		List<String>list = t.inOrder();
//
//		for (String s : list) {
//			System.out.println(s);
//		}

//		Node<Integer> t = new Node<Integer>(8);
//
//		t.insert(10);
//		t.insert(49);
//		t.insert(5);
//		t.insert(6);
//		t.insert(7);
//		t.insert(15);
//		t.insert(24);
//		t.insert(13);
//		t.insert(35);
//		t.insert(46);
//
//
//		List<Integer> list = t.inOrder();
//		for (Integer i : list) {
//			System.out.printf("%d ", i);
//		}
//
//		System.out.println();
//
//		list = t.preOrder();
//		for (Integer i : list) {
//			System.out.printf("%d ", i);
//		}
//
//		System.out.println();
//
//		list = t.postOrder();
//		for (Integer i : list) {
//			System.out.printf("%d ", i);
//		}
//
//		System.out.println();
//		System.out.println();
//		System.out.println();
//
//		t.delete(8);
//
//		list = t.inOrder();
//		for (Integer i : list) {
//			System.out.printf("%d ", i);
//		}
//
//		System.out.println();
//
//		list = t.preOrder();
//		for (Integer i : list) {
//			System.out.printf("%d ", i);
//		}
//
//		System.out.println();
//
//		list = t.postOrder();
//		for (Integer i : list) {
//			System.out.printf("%d ", i);
//		}

//		t.mostraArvore();
	}

	private static int readInt(String msg) {
		System.out.println(msg);
		return reader.nextInt();
	}
}