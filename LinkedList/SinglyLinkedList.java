// Implements a singly-linked list.

public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	public static void main(String[] args) {
		Integer[] arr = new Integer[]{1, 2, 3};
		SinglyLinkedList<Integer> test = new SinglyLinkedList<Integer>(arr);
		System.out.println(test.toString());
		Integer hell = null;
		test.add(hell);
		System.out.println(test.toString());
	}

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	@SuppressWarnings("unchecked")
	public SinglyLinkedList(Object[] values) {
		head = new ListNode<E>((E) values[0], tail);
		tail = head;
		for (int i = 1; i < values.length; i++) {
			add((E) values[i]);
			nodeCount++;
		}
	}
	
	public ListNode<E> getHead() {
		return head;
	}
	
	public ListNode<E> getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		ListNode<E> next = head;
		for (int i = 0; i < nodeCount; i++) {
			if (!next.equals(null)) {
				return false;
			}

			next = next.getNext();
		}

		return true;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		ListNode<E> next = head;
		while (!next.equals(tail)) {
			if (next.equals(obj)) {
				return true;
			}
			next = next.getNext();
		}

		if (tail.equals(obj)) {
			return true;
		}

		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		int idx = 0;
		ListNode<E> next = head;
		while (!next.equals(tail)) {
			if (next.equals(obj)) {
				return idx;
			}

			idx++;
			next = next.getNext();
		}

		if (tail.equals(obj)) {
			return idx;
		}

		return -1;
	}

	// Adds obj to this collection.  Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		ListNode<E> next = new ListNode<E>(obj);
		if (nodeCount == 0) {
			head = next;
			tail = next;
		}

		tail.setNext(next);
		tail = next;
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		int idx = indexOf(obj);
		if (idx == -1) {
			return false;
		}

		remove(idx);
		return true;
	}

	// Returns the i-th element.               
	public E get(int i) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> next = head;
		for (int _i = 0; _i < i; _i++) {
			next = next.getNext();
		}

		return next.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	@SuppressWarnings("unchecked")
	public E set(int i, Object obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}

		ListNode<E> next = head;
		for (int _i = 0; _i < i - 1; _i++) {
			next = next.getNext();
		}
		
		E temp = next.getNext().getValue();
		ListNode<E> newNode = new ListNode<E>((E) obj, next.getNext().getNext());
		next.setNext(newNode);

		return temp;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	@SuppressWarnings({ "unchecked"})
	public void add(int i, Object obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}

		if (i == 0) {
			head = new ListNode<E>((E) obj, head);
		}

		ListNode<E> next = head;
		for (int _i = 0; _i < i - 1; _i++) {
			next = next.getNext();
		}

		ListNode<E> added = new ListNode<E>((E) obj, next.getNext().getNext());
		nodeCount++;
		next.setNext(added);
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException();
		}

		if (i == 0) {
			E removed = head.getValue();
			head = head.getNext();
			return removed;
		}

		ListNode<E> next = head;
		for (int _i = 0; _i < i - 1; _i++) {
			next = next.getNext();
		}

		if (next.getNext().equals(tail)) {
			E removed = tail.getValue();
			tail = next;
			return removed;
		}

		E removed = next.getNext().getValue();
		next.setNext(next.getNext().getNext());
		nodeCount--;
		return removed;
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		if (head == null) {
			str.append("null");
		} else {
			str.append(head.getValue().toString());
		}

		ListNode<E> next = head;
		while (!next.equals(tail)) {	
			next = next.getNext();
			if (next == null) {
				str.append(", null");
			} else {
				str.append(", ");
				str.append(next.getValue().toString());
			}			
		}
		
		str.append("]");
		return str.toString();
	}
}
