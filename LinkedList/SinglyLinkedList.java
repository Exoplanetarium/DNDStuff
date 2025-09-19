// Implements a singly-linked list.

public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	public static void main(String[] args) {
		Integer[] arr = new Integer[]{1, 2, 3};
		SinglyLinkedList<Integer> test = new SinglyLinkedList<Integer>(arr);
		System.out.println(test.toString());
		Integer hell = 6;
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

	}

	// Returns the number of elements in this list.
	public int size() {
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
	}

	// Adds obj to this collection.  Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		ListNode<E> next = new ListNode<E>(obj);
		tail.setNext(next);
		tail = next;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		
	}

	// Returns the i-th element.               
	public E get(int i) {
		if (i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}

		for (ListNode<E> next = head; !next.equals(tail); next = next.getNext()) {
			i--;
			if (i == 0) {
				return next.getValue();
			} 
		}

		return null;
	}

	// Replaces the i-th element with obj and returns the old value.
	@SuppressWarnings("unchecked")
	public E set(int i, Object obj) {
		ListNode<E> oldNode = new ListNode<E>(get(i));
		
		return oldNode.getValue();
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		str.append(head.getValue().toString());

		ListNode<E> next = head;
		while (!next.equals(tail)) {	
			next = next.getNext();
			str.append(", ");
			str.append(next.getValue().toString());
		}
		
		str.append("]");
		return str.toString();
	}

	public void boundsCheck() {
		if (head == null || tail == null) {
			throw new NullPointerException();
		}
	}
}
