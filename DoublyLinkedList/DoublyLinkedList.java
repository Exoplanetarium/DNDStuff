
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	public static void main(String[] args) {
		Nucleotide[] vals = new Nucleotide[] { Nucleotide.A, Nucleotide.G, null, Nucleotide.T };
		Nucleotide[] vals2 = new Nucleotide[] { Nucleotide.C, Nucleotide.C, null, Nucleotide.A, Nucleotide.G, Nucleotide.C, Nucleotide.C, null, Nucleotide.A, Nucleotide.G, Nucleotide.C, Nucleotide.C, null, Nucleotide.A, Nucleotide.G, Nucleotide.C, Nucleotide.C, null, Nucleotide.A, Nucleotide.G };
		Nucleotide[] vals3 = new Nucleotide[] { Nucleotide.C, Nucleotide.C, Nucleotide.A };

		DoublyLinkedList test = new DoublyLinkedList(vals);
		DoublyLinkedList test2 = new DoublyLinkedList(vals2);
		DoublyLinkedList test3 = new DoublyLinkedList(vals3);
		System.out.println(test.toString());
		test.addSegmentToEnd(test2);
		System.out.println(test.toString());
		ListNode2<Nucleotide> node = test.getHead().getNext().getNext();
		test.removeCCCCCCCCGGGGGGGG(node);
		System.out.println(test.toString());
		test.deleteSegment(test3);
		System.out.println(test.toString());
		test.deleteLastThree();
		System.out.println(test.toString());
		test.replaceEveryAWithTAC();
		System.out.println(test.toString());
	}

	private final ListNode2<Nucleotide> SENTINEL = new ListNode2<Nucleotide>(null);
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		nodeCount = 0;
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);

		for (int i = 0; i < values.length; i++) {
			add(values[i]);
		}
	}

	public ListNode2<Nucleotide> getSentinel() {
		return SENTINEL;
	}

	public ListNode2<Nucleotide> getHead() {
		return SENTINEL.getNext();
	}

	public ListNode2<Nucleotide> getTail() {
		return SENTINEL.getPrevious();
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int i = 0; i < nodeCount; i++) {
			if (node.getValue() != null) {
				return false;
			}	

			node = node.getNext();
		}

		return true;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(Nucleotide obj) {
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int i = 0; i < nodeCount; i++) {
			if (node.getValue() == null || obj == null) {
				if (node.getValue() != null || obj != null) {
					continue;
				} else {
					return true;
				}
			}

			if (node.getValue().equals(obj)) {
				return true;
			}

			node = node.getNext();
		}

		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int i = 0; i < nodeCount; i++) {
			if (node.getValue() == null || obj == null) {
				if (node.getValue() != null || obj != null) {
					continue;
				} else {
					return i;
				}
			}

			if (node.getValue().equals(obj)) {
				return i;
			}
			node = node.getNext();
		}

		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(Nucleotide obj) {
		ListNode2<Nucleotide> last = new ListNode2<Nucleotide>(obj);

		ListNode2<Nucleotide> prev = getTail();
		last.setPrevious(prev);
		prev.setNext(last);

		last.setNext(SENTINEL);
		SENTINEL.setPrevious(last);

		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int _i = 0; _i < nodeCount; _i++) {
			if (node.getValue() == null || obj == null) {
				if (node.getValue() != null || obj != null) {
					continue;
				} else {
					ListNode2<Nucleotide> prev = node.getPrevious();
					ListNode2<Nucleotide> next = node.getNext();
					prev.setNext(next);
					next.setPrevious(prev);

					nodeCount--;
					return true;
				}
			}

			if (node.getValue().equals(obj)) {
				ListNode2<Nucleotide> prev = node.getPrevious();
				ListNode2<Nucleotide> next = node.getNext();
				prev.setNext(next);
				next.setPrevious(prev);

				nodeCount--;
				return true;
			}

			node = node.getNext();
		}

		return false;
	}

	// Returns the i-th element.
	public Nucleotide get(int i) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException();
		}

		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int _i = 0; _i < i; _i++) {
			node = node.getNext();
		}

		return node.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException();
		}

		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int _i = 0; _i < i; _i++) {
			node = node.getNext();
		}

		ListNode2<Nucleotide> prev = node.getPrevious();
		ListNode2<Nucleotide> next = node.getNext();

		Nucleotide old = node.getValue();
		ListNode2<Nucleotide> newNode = new ListNode2<Nucleotide>(obj);
		newNode.setNext(next);
		newNode.setPrevious(prev);

		prev.setNext(newNode);
		next.setPrevious(newNode);
		return old;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException();
		}

		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int _i = 0; _i < i; _i++) {
			node = node.getNext();
		}

		ListNode2<Nucleotide> prev = node.getPrevious();

		ListNode2<Nucleotide> newNode = new ListNode2<Nucleotide>(obj);
		newNode.setNext(node);
		newNode.setPrevious(prev);

		prev.setNext(newNode);
		node.setPrevious(newNode);

		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}

		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int _i = 0; _i < i; _i++) {
			node = node.getNext();
		}

		ListNode2<Nucleotide> next = node.getNext();
		ListNode2<Nucleotide> prev = node.getPrevious();

		Nucleotide old = node.getValue();

		prev.setNext(next);
		next.setPrevious(prev);
		nodeCount--;
		return old;
	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	public String toString() {
		StringBuilder arr = new StringBuilder("[");
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int i = 0; i < nodeCount - 1; i++) {
			if (node.getValue() == null) {
				arr.append("null, ");
				node = node.getNext();
				continue;
			}

			arr.append(node.getValue().toString());
			arr.append(", ");
			node = node.getNext();
		}

		if (SENTINEL.getPrevious().getValue() == null) {
			arr.append("null]");
			return arr.toString();
		}

		arr.append(SENTINEL.getPrevious().getValue().toString());
		arr.append("]");
		return arr.toString();

	}

	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {
		for (int i = 0; i < seg.size(); i++) {
			add(seg.get(i));
		}
	}

	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		ListNode2<Nucleotide> node = getHead();
		int index = 0;
		for (int i = 0; i < nodeCount; i++) {
			if (node.equals(nodeBefore)) {
				index = i + 1;
				break;
			}
			node = node.getNext();
		}

		for (int i = 0; i < 16; i++) {
			if (index >= nodeCount) {
				break;
			}

			remove(index);
		}
	}

	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {
		int rolling = 0;
		int isRolling = 0;
		int segCount = 0;
		for (int i = rolling; i < seg.size() + rolling; i += isRolling) {
			if (seg.size() + rolling >= nodeCount) {
				return false;
			}

			if (segCount == seg.size()) {
				for (int j = seg.size() + rolling - 1; j >= rolling; j--) {
					remove(j);
				}
				return true;
			}

			if (get(i) == null || seg.get(0) == null) {
				if (seg.get(0) != null || get(i) != null) {
					isRolling = 0;
					rolling++;
					i = rolling;
					continue;
				} else {
					isRolling = 1;
					if (get(i) == null || seg.get(segCount) == null) {
						if (seg.get(0) != null || get(i) != null) {
							segCount = 0;
							isRolling = 0;
							continue;
						} else {
							continue;
						}
					} else if (!get(i).equals(seg.get(segCount))) {
						segCount = 0;
						isRolling = 0;
						continue;
					} else {
						segCount++;
						continue;
					}
				}
			} 

			if (!get(i).equals(seg.get(0))) {
				isRolling = 0;
				rolling++;
				i = rolling;
			} else {
				isRolling = 1;
				if (!get(i).equals(seg.get(segCount))) {
					segCount = 0;
					isRolling = 0;
					continue;
				} else {
					segCount++;
				}
			}

		}

		return false;
	}

	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {
		if (nodeCount - 3 < 0) {
			return false;
		}

		for (int i = 0; i < 3; i++) {
			remove(nodeCount - 1);
		}

		return true;
	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int i = 0; i < nodeCount; i++) {
			if (node.getValue() == null) {
				continue;
			}

			if (node.getValue().equals(Nucleotide.A)) {
				ListNode2<Nucleotide> T = new ListNode2<Nucleotide>(Nucleotide.T);
				ListNode2<Nucleotide> C = new ListNode2<Nucleotide>(Nucleotide.C);

				T.setNext(node);
				C.setPrevious(node);

				ListNode2<Nucleotide> prev = node.getPrevious();
				ListNode2<Nucleotide> next = node.getNext();

				prev.setNext(T);
				next.setPrevious(C);

				node = node.getNext();
				i++;
			}

			node = node.getNext();
		}
	}

}
