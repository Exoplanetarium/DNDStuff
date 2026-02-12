import java.util.LinkedList;

public class MyPQ<E extends Comparable<E>> implements MyPriorityQueue<E>{
    private LinkedList<E> pq;
	private int objectCount;

	public MyPQ()
	{
		this.pq = new LinkedList<E>();
		this.objectCount = 0;
	}


    public E peek() {
		if (!isEmpty()) {
			return pq.getFirst();
		}

		return null;
	}

	public boolean isEmpty() {
		if (objectCount == 0) {
			return true;
		}

		return false;
	}

	public E removeMin() {
		if (isEmpty()) {
			return null;
		}

		E temp = pq.getFirst();
		pq.removeFirst();
		objectCount--;
		return temp;
	}

	public void add(E obj) {
		pq.add(obj);
	}
	
}
