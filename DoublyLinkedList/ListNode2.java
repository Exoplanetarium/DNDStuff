// Represents a node of a doubly-linked list.

public class ListNode2<E> {
	private E value;
	private ListNode2<E> previous;
	private ListNode2<E> next;


	public ListNode2(E v) {
		value = v;
		previous = null;
		next = null;
	}

	public ListNode2(E v, ListNode2<E> prev, ListNode2<E> nx) {
		value = v;
		previous = prev;
		next = nx;
	}

	public E getValue() { return value; }
	public ListNode2<E> getPrevious() { return previous; }
	public ListNode2<E> getNext() { return next; }

	public void setValue(E v) { value = v; }
	public void setPrevious(ListNode2<E> previous) { this.previous = previous; }
	public void setNext(ListNode2<E> nx) { next = nx; }
}


