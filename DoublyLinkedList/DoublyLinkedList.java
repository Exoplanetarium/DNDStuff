
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	public static void main(String[] args) {
		Nucleotide[] vals = new Nucleotide[]{Nucleotide.A, Nucleotide.G, null, Nucleotide.T};
		DoublyLinkedList test = new DoublyLinkedList(vals);
		System.out.println(test.toString());
		test.add(Nucleotide.A);
		test.remove(null);
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
			if (!node.equals(null)) {
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
			if (node.getValue().equals(obj)) {
				return i;
			}
			node = node.getNext();
		}

		return -1;
	}

	// Adds obj to this collection.  Returns true if successful;
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
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int _i = 0; _i < i; _i++) {
			node = node.getNext();
		}

		return node.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int _i = 0; _i < i; _i++) {
			node = node.getNext();
		}

		Nucleotide old = node.getValue();
		ListNode2<Nucleotide> newNode = new ListNode2<Nucleotide>(obj);
		newNode.setNext(node.getNext());
		newNode.setPrevious(node.getPrevious());
		return old;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int _i = 0; _i < i; _i++) {
			node = node.getNext();
		}

		ListNode2<Nucleotide> newNode = new ListNode2<Nucleotide>(obj);
		newNode.setNext(node);
		newNode.setPrevious(node.getPrevious().getPrevious());
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int _i = 0; _i < i; _i++) {
			node = node.getNext();
		}

		Nucleotide old = node.getValue();
		node.getNext().setNext(node.getNext());
		node.getPrevious().setNext(node.getPrevious());
		return old;
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder arr = new StringBuilder("[");
		ListNode2<Nucleotide> node = SENTINEL.getNext();
		for (int i = 0; i < nodeCount; i++) {
			if (node.getValue() == null) {
				arr.append("null, ");
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
		
	}
	
	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		
	}
	
	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {
		
	}
	
	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {
		
	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {
		
	}

}
