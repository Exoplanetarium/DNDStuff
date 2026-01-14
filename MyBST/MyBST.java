// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {
	public static void main(String[] args) {
		MyBST<Integer> test = new MyBST<Integer>();
		test.add(15);
		test.add(20);
		test.add(10);
		test.add(25);
		test.add(5);
		test.add(10);
		test.add(22);
		test.add(12);
		test.add(16);
		test.add(2);
		System.out.println(test.toString());
		System.out.println(test.toHeight());
		test.remove(15);
		test.remove(2);
		test.remove(2);
		System.out.println("Has fifteen: " + test.contains(15));
		System.out.println("Has sixteen: " + test.contains(16));
		System.out.println("min, max: " + test.min() + ", " + test.max());
		System.out.println(test.toString());
		System.out.println(test.toHeight());

		MyBST<Integer> testRoot = new MyBST<Integer>();
		testRoot.add(1);
		testRoot.add(2);
		testRoot.add(3);
		testRoot.remove(1);
		System.out.println("min, max: " + testRoot.min() + ", " + testRoot.max());
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
		if (value == null) {
			throw new IllegalArgumentException();
		}

		if (root == null) {
			return false;
		}

		BinaryNode<E> toBeRemoved = removeHelper(root, value);
		if (toBeRemoved == null) {
			return false;
		}

		return true;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}

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
			newNode.setParent(parent);
			newNode.setHeight(parent.getHeight() + 1);
		} else if (value.compareTo(parent.getValue()) > 0) {
			parent.setRight(newNode);
			newNode.setParent(parent);
			newNode.setHeight(parent.getHeight() + 1);
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
		if (value == null) {
			throw new IllegalArgumentException();
		}

		if (root == null) {
			return false;
		}

		if (root.isLeaf()) {
			if (root.getValue().compareTo(value) == 0) {
				root = null;
				return true;
			} else {
				return false;
			}
		}

		BinaryNode<E> toBeRemoved = removeHelper(root, value);

		if (toBeRemoved == null) {
			return false;
		}

		if (toBeRemoved.isLeaf()) {
			if (toBeRemoved.getParent().getLeft() == null) {
				toBeRemoved.getParent().setRight(null);
			} else if (toBeRemoved.getParent().getRight() == null) {
				toBeRemoved.getParent().setLeft(null);
			} else if (toBeRemoved.getParent().getLeft().equals(toBeRemoved)) {
				toBeRemoved.getParent().setLeft(null);
			} else if (toBeRemoved.getParent().getRight().equals(toBeRemoved)) {
				toBeRemoved.getParent().setRight(null);
			}
		} else if (toBeRemoved.hasRight()) {
			BinaryNode<E> toReplace = toBeRemoved.getRight();
			if (!toReplace.hasLeft()) {
				E tempValue = toReplace.getValue();
				BinaryNode<E> temp = toReplace.getRight();
				temp.setParent(toReplace.getParent());
				toReplace.getParent().setRight(temp);
				temp.setHeight(temp.getHeight() - 1);
				toBeRemoved.setValue(tempValue);
				return true;
			}

			while (toReplace.hasLeft()) {
				toReplace = toReplace.getLeft();
			}

			E tempValue = toReplace.getValue();

			if (toReplace.hasRight()) {
				BinaryNode<E> temp = toReplace.getRight();
				temp.setParent(toReplace.getParent());
				toReplace.getParent().setLeft(temp);
				temp.setHeight(temp.getHeight() - 1);
				toReplace = temp;
			} else {
				if (toReplace.getParent().equals(toBeRemoved)) {
					toReplace.getParent().setRight(null);
				} else {
					toReplace.getParent().setLeft(null);
				}
			}

			toBeRemoved.setValue(tempValue);
		} else {
			toBeRemoved.setValue(toBeRemoved.getLeft().getValue());
			toBeRemoved.setLeft(null);
		}

		return true;
	}

	public BinaryNode<E> removeHelper(BinaryNode<E> node, E value) {
		if (value.compareTo(node.getValue()) == 0) {
			return node;
		}

		if (value.compareTo(node.getValue()) < 0) {
			if (node.hasLeft()) {
				return removeHelper(node.getLeft(), value);
			}
		} else if (value.compareTo(node.getValue()) > 0) {
			if (node.hasRight()) {
				return removeHelper(node.getRight(), value);
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
			return minHelper(node.getLeft());
		}

		return node.getValue();
	}
	
	// Returns the maximum in the tree.
	public E max() {
		return maxHelper(root);
	}

	public E maxHelper(BinaryNode<E> node) {
		if (node.hasRight()) {
			return maxHelper(node.getRight());
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

	public String toHeight() {
		StringBuilder str = new StringBuilder("[");
		toHeightHelper(root, str);
		str.replace(str.length() - 2, str.length(), "]");
		return str.toString();
	}

	public void toHeightHelper(BinaryNode<E> node, StringBuilder string) {
		if (node.hasLeft()) {
			toHeightHelper(node.getLeft(), string);
		}

		string.append(node.getHeight()  + ", ");

		if (node.hasRight()) {
			toHeightHelper(node.getRight(), string);
		}
	}
}
