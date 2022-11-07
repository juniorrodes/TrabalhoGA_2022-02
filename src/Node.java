//Pra que serve
//Tantos C�digos?
//Se a vida
//n�o � programada
//e as melhores coisas
//n�o tem l�gica

public class Node {
	private int balanceFactor;
	private final int key;
	private int height;

	private Node leftChild;
	private Node rightChild;

	public Node(int key) {
		this.leftChild = null;
		this.rightChild = null;
		this.key = key;
		this.height = 1;
		this.balanceFactor = 0;
	}

	public Node find(int value) {
		if (value > this.key) {
			return this.rightChild.find(value);
		} else if (value < this.key) {
			return this.leftChild.find(value);
		} else {
			return this;
		}
	}

	public void insert(int value) {
		if (this.find(value) == null) {
			Node newNode = new Node(value);
			if (value < this.key) {
				if (this.leftChild == null) {
					this.leftChild = newNode;
				} else {
					this.leftChild.insert(value);
				}
			} else {
				if (this.rightChild == null) {
					this.rightChild = newNode;
				} else {
					this.rightChild.insert(value);
				}
			}
			this.updateBalance();
			this.updateTreeHeight();
		}
	}

	public void updateBalance() {
		int leftTreeHeight = this.leftChild.height;
		int rightTreeHeight = this.rightChild.height;

		this.balanceFactor = leftTreeHeight - rightTreeHeight;
	}

	public void updateTreeHeight() {
		int leftTreeHeight = this.leftChild.height;
		int rightTreeHeight = this.rightChild.height;

		this.height = Math.max(leftTreeHeight, rightTreeHeight) + 1;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public Node setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
		return leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public Node setRightChild(Node rightChild) {
		this.rightChild = rightChild;
		return rightChild;
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public int getKey() {
		return key;
	}


	public String toString() {
		return Integer.toString(getKey());
	}
}
