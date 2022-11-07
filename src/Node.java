//Pra que serve
//Tantos C�digos?
//Se a vida
//n�o � programada
//e as melhores coisas
//n�o tem l�gica

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int balanceFactor;
	private int key;
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

	private Node(int key, int height, int balanceFactor) {
		this.leftChild = null;
		this.rightChild = null;
		this.key = key;
		this.height = height;
		this.balanceFactor = balanceFactor;
	}

	public Node find(int value) {
		if (value > this.key && this.rightChild != null) {
			return this.rightChild.find(value);
		}
		if (value < this.key && this.leftChild != null) {
			return this.leftChild.find(value);
		}
		if (value == this.key) {
			return this;
		}

		return null;
	}

	private Node findMax() {
		return this.rightChild == null ? this : this.rightChild.findMax();
	}

	public Node delete(int value) {
		if (find(value) == null) {
			return null;
		}

		Node newNode = null;

		if (value < this.key) {
			this.leftChild = this.leftChild.delete(value);
			newNode = this;
		} else if (value > this.key) {
			this.rightChild = this.rightChild.delete(value);
			newNode = this;
		} else {
			if (this.leftChild == null) {
				newNode = this.rightChild;
			} else if (this.rightChild == null) {
				newNode = this.leftChild;
			} else {
				Node max = this.leftChild.findMax();

				this.key = max.key;
				this.leftChild = this.leftChild.delete(max.key);

				newNode = this;
			}
		}

		this.updateBalance();
		this.updateTreeHeight();
		return newNode;
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

	public void updateTreeHeight() {
		int leftTreeHeight = this.leftChild != null ? this.leftChild.height : 0;
		int rightTreeHeight = this.rightChild != null ? this.rightChild.height : 0;

		this.height = Math.max(leftTreeHeight, rightTreeHeight) + 1;
	}

	public void updateBalance() {
		int leftTreeHeight = this.leftChild != null ? this.leftChild.height : 0;
		int rightTreeHeight = this.rightChild != null ? this.rightChild.height : 0;

		this.balanceFactor = leftTreeHeight - rightTreeHeight;

		if (this.balanceFactor <= -2 || this.balanceFactor >= 2) {
			Node newNode = this.balanceTree();

			this.updateNodeReference(newNode);
		}
	}

	private void updateNodeReference(Node newNode) {
		Node copy = new Node(this.key, this.height, this.balanceFactor);
		copy.leftChild = this.leftChild;
		copy.rightChild = this.rightChild;

		this.key = newNode.key;
		this.height = newNode.height;
		this.balanceFactor = newNode.balanceFactor;

		if (newNode.leftChild == this) {
			this.leftChild = copy;
			this.rightChild = newNode.rightChild;
		} else if (newNode.rightChild == this) {
			this.rightChild = copy;
			this.leftChild = newNode.leftChild;
		}
	}

	private Node balanceTree() {
		if (this.balanceFactor == 2) {
			if (this.leftChild.balanceFactor == -1) {
				this.leftChild= this.leftChild.rotateLeft();
			}

			return this.rotateRight();
		} else if (this.balanceFactor == -2) {
			if(this.rightChild.balanceFactor == 1) {
				this.rightChild = this.rightChild.rotateRight();
			}

			return this.rotateLeft();
		}

		return this;
	}

	private Node rotateLeft() {
		Node newRoot;

		newRoot = this.rightChild;
		this.rightChild = newRoot.leftChild;
		newRoot.leftChild = this;

		updateAttributes(newRoot);

		return newRoot;
	}

	private Node rotateRight() {
		Node newRoot;

		newRoot = this.leftChild;
		this.leftChild = newRoot.rightChild;
		newRoot.rightChild = this;

		updateAttributes(newRoot);

		return newRoot;
	}

	private void updateAttributes(Node newRoot) {
		this.updateBalance();
		this.updateTreeHeight();
		newRoot.updateBalance();
		newRoot.updateTreeHeight();
	}

	public List<Integer> inOrder() {
		List<Integer> list = new ArrayList<Integer>();

		if (this.leftChild != null) {
			list.addAll(this.leftChild.inOrder());
		}
		list.add(this.key);
		if (this.rightChild != null) {
			list.addAll(this.rightChild.inOrder());
		}

		return list;
	}

	public List<Integer> preOrder() {
		List<Integer> list = new ArrayList<Integer>();

		list.add(this.key);
		if (this.leftChild != null) {
			list.addAll(this.leftChild.preOrder());
		}
		if (this.rightChild != null) {
			list.addAll(this.rightChild.preOrder());
		}

		return list;
	}

	public List<Integer> postOrder() {
		List<Integer> list = new ArrayList<Integer>();

		if (this.leftChild != null) {
			list.addAll(this.leftChild.postOrder());
		}
		if (this.rightChild != null) {
			list.addAll(this.rightChild.postOrder());
		}
		list.add(this.key);

		return list;
	}

	public int getKey() {
		return key;
	}


	public String toString() {
		return Integer.toString(getKey());
	}
}
