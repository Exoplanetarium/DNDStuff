// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {
	public static void main(String[] args) {
		MyBST<Integer> test = new MyBST<Integer>();
		test.add(15);
		test.add(20);
		test.add(10);
		test.add(25);
		test.add(5);
		System.out.println(test.toString());

	}
	private BinaryNode<E> root;  // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}
	
	public int getHeight() {
		return root.getHeight();
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		return false;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		if (root == null) {
			BinaryNode<E> newRoot = new BinaryNode<E>(value);
			root = newRoot;
			return true;
		}

		BinaryNode<E> parent = addHelper(root, value);
		
		if (parent == null) {
			return false;
		}
		
		BinaryNode<E> newNode = new BinaryNode<E>(value);
		if (value.compareTo(parent.getValue()) < 0) {
			parent.setLeft(newNode);
		} else if (value.compareTo(parent.getValue()) > 0) {
			parent.setRight(newNode);
		}

		return true;
	}

	public BinaryNode<E> addHelper(BinaryNode<E> node, E value) {
		if (value.compareTo(node.getValue()) == 0) {
			return null;
		}

		if (value.compareTo(node.getValue()) < 0) {
			if (node.hasLeft()) {
				node = addHelper(node.getLeft(), value);
			} else {
				return node;
			}
		} else if (value.compareTo(node.getValue()) > 0) {
			if (node.hasRight()) {
				node = addHelper(node.getRight(), value);
			} else {
				return node;
			}
		}
		
		return node;
	}

	// Removes value from this BST.  Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	//  largest node in the right subtree
	public boolean remove(E value) {
		BinaryNode<E> toBeRemoved = removeHelper(root, value);

		if (toBeRemoved == null) {
			return false;
		}

		if (toBeRemoved.isLeaf()) {
			toBeRemoved = null;
		} else if (toBeRemoved.hasLeft() && toBeRemoved.hasRight()) {
			if (toBeRemoved.getParent().getLeft().equals(toBeRemoved)) {
				toBeRemoved.getParent().setLeft(toBeRemoved.getRight());
			} else if (toBeRemoved.getParent().getRight().equals(toBeRemoved)) {
				toBeRemoved.getParent().setRight(toBeRemoved.getRight());
			}
		}
	}

	public BinaryNode<E> removeHelper(BinaryNode<E> node, E value) {
		if (value.compareTo(node.getValue()) == 0) {
			return node;
		}

		if (value.compareTo(node.getValue()) < 0) {
			if (node.hasLeft()) {
				node = addHelper(node.getLeft(), value);
			}
		} else if (value.compareTo(node.getValue()) > 0) {
			if (node.hasRight()) {
				node = addHelper(node.getRight(), value);
			}
		}

		return null;
	}
	
	// Returns the minimum in the tree
	public E min() {
		return minHelper(root);
	}

	public E minHelper(BinaryNode<E> node) {
		if (node.hasLeft()) {
			minHelper(node.getLeft());
		}

		return node.getValue();
	}
	
	// Returns the maximum in the tree.
	public E max() {
		return maxHelper(root);
	}

	public E maxHelper(BinaryNode<E> node) {
		if (node.hasRight()) {
			minHelper(node.getRight());
		}

		return node.getValue();
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		toStringHelper(root, str);
		str.replace(str.length() - 2, str.length(), "]");
		return str.toString();
	}

	public void toStringHelper(BinaryNode<E> node, StringBuilder string) {
		if (node.hasLeft()) {
			toStringHelper(node.getLeft(), string);
		}

		string.append(node.getValue()  + ", ");

		if (node.hasRight()) {
			toStringHelper(node.getRight(), string);
		}
	}
}
