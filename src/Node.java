//Pra que serve
//Tantos C�digos?
//Se a vida
//n�o � programada
//e as melhores coisas
//n�o tem l�gica

import java.util.ArrayList;
import java.util.List;

public class Node<T extends Comparable<T>> {
	private int balanceFactor;
	private T key;
	private int index;
	private int height;

	private Node<T> leftChild;
	private Node<T> rightChild;

	public Node(T key, int index) {
		this.leftChild = null;
		this.rightChild = null;
		this.key = key;
		this.index = index;
		this.height = 1;
		this.balanceFactor = 0;
	}

	private Node(T key, int index, int height, int balanceFactor) {
		this.leftChild = null;
		this.rightChild = null;
		this.key = key;
		this.height = height;
		this.index = index;
		this.balanceFactor = balanceFactor;
	}

	public Node<T> find(T value) {
		if (value.compareTo(this.key) > 0 && this.rightChild != null) {
			return this.rightChild.find(value);
		}
		if (value.compareTo(this.key) < 0 && this.leftChild != null) {
			return this.leftChild.find(value);
		}
		if (value.equals(this.key)) {
			return this;
		}

		return null;
	}

	public Node<T> findName(T nameToSearch) {
		if (this.key.toString().startsWith(nameToSearch.toString())) {
			return this;
		}
		if (nameToSearch.compareTo(this.key) > 0 && this.rightChild != null) {
			return this.rightChild.findName(nameToSearch);
		}
		if (nameToSearch.compareTo(this.key) < 0 && this.leftChild != null) {
			return this.leftChild.findName(nameToSearch);
		}

		return null;
	}

	public int search(T value) {
		if (this.find(value) == null) {
			return -1;
		}

		if (value.compareTo(this.key) < 0) {
			return this.leftChild.search(value);
		} else if (value.compareTo(this.key) > 0) {
			return this.rightChild.search(value);
		}

		return this.index;
	}

	public List<Integer> searchName(T nameToSearch){
		if (this.findName(nameToSearch) == null) {
			return null;
		}
		List<Integer> list = new ArrayList<Integer>();
		int a = nameToSearch.compareTo(this.key);
		if (nameToSearch.compareTo(this.key) < 0 && this.leftChild != null) {
			list.addAll(this.leftChild.searchName(nameToSearch));
		}
		if (nameToSearch.compareTo(this.key) > 0 && this.rightChild != null) {
			list.addAll(this.rightChild.searchName(nameToSearch));
		}

		if (this.key.toString().startsWith(nameToSearch.toString())) {
			list.add(this.index);
		}

		return list;
	}

	private Node<T> findMax() {
		return this.rightChild == null ? this : this.rightChild.findMax();
	}

	public Node<T> delete(T value) {
		if (find(value) == null) {
			return null;
		}

		Node<T> newNode = null;

		if (this.key.compareTo(value) < 0) {
			this.leftChild = this.leftChild.delete(value);
			newNode = this;
		} else if (this.key.compareTo(value) > 0) {
			this.rightChild = this.rightChild.delete(value);
			newNode = this;
		} else {
			if (this.leftChild == null) {
				newNode = this.rightChild;
			} else if (this.rightChild == null) {
				newNode = this.leftChild;
			} else {
				Node<T> max = this.leftChild.findMax();

				this.key = max.key;
				this.leftChild = this.leftChild.delete(max.key);

				newNode = this;
			}
		}

		this.updateBalance();
		this.updateTreeHeight();
		return newNode;
	}

	public void insert(T value, int index) {
		if (this.find(value) == null) {
			Node<T> newNode = new Node<T>(value, index);
			if (value.compareTo(this.key) < 0) {
				if (this.leftChild == null) {
					this.leftChild = newNode;
				} else {
					this.leftChild.insert(value, index);
				}
			} else {
				if (this.rightChild == null) {
					this.rightChild = newNode;
				} else {
					this.rightChild.insert(value, index);
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
			Node<T> newNode = this.balanceTree();

			this.updateNodeReference(newNode);
		}
	}

	private void updateNodeReference(Node<T> newNode) {
		Node<T> copy = new Node<T>(this.key, this.index, this.height, this.balanceFactor);
		copy.leftChild = this.leftChild;
		copy.rightChild = this.rightChild;

		this.key = newNode.key;
		this.height = newNode.height;
		this.index = newNode.index;
		this.balanceFactor = newNode.balanceFactor;

		if (newNode.leftChild == this) {
			this.leftChild = copy;
			this.rightChild = newNode.rightChild;
		} else if (newNode.rightChild == this) {
			this.rightChild = copy;
			this.leftChild = newNode.leftChild;
		}
	}

	private Node<T> balanceTree() {
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

	private Node<T> rotateLeft() {
		Node<T> newRoot;

		newRoot = this.rightChild;
		this.rightChild = newRoot.leftChild;
		newRoot.leftChild = this;

		updateAttributes(newRoot);

		return newRoot;
	}

	private Node<T> rotateRight() {
		Node<T> newRoot;

		newRoot = this.leftChild;
		this.leftChild = newRoot.rightChild;
		newRoot.rightChild = this;

		updateAttributes(newRoot);

		return newRoot;
	}

	private void updateAttributes(Node<T> newRoot) {
		this.updateBalance();
		this.updateTreeHeight();
		newRoot.updateBalance();
		newRoot.updateTreeHeight();
	}

	public List<T> inOrder() {
		List<T> list = new ArrayList<T>();

		if (this.leftChild != null) {
			list.addAll(this.leftChild.inOrder());
		}
		list.add(this.key);
		if (this.rightChild != null) {
			list.addAll(this.rightChild.inOrder());
		}

		return list;
	}

	public List<T> preOrder() {
		List<T> list = new ArrayList<T>();

		list.add(this.key);
		if (this.leftChild != null) {
			list.addAll(this.leftChild.preOrder());
		}
		if (this.rightChild != null) {
			list.addAll(this.rightChild.preOrder());
		}

		return list;
	}

	public List<T> postOrder() {
		List<T> list = new ArrayList<T>();

		if (this.leftChild != null) {
			list.addAll(this.leftChild.postOrder());
		}
		if (this.rightChild != null) {
			list.addAll(this.rightChild.postOrder());
		}
		list.add(this.key);

		return list;
	}

	public String toString() {
		var indentation = "";
		for (int i = 1; i <= this.height; i++) {
			indentation = String.format("%s\t", indentation);
		}
		if ((this.leftChild == null) && (this.rightChild != null)) {
			return String.format("%s%s\n%s", indentation, this.key, this.rightChild);
		}
		if ((this.leftChild != null) && (this.rightChild == null)) {
			return String.format("%s%s\n%s", indentation, this.key, this.leftChild);
		}
		if ((this.leftChild == null) && (this.rightChild == null)) {
			return String.format("%s%s", indentation, this.key);
		}
		return String.format("%s%s\n%s\n%s", indentation, this.key, this.leftChild, this.rightChild);
	}
}
