public class MyPQ<E extends Comparable<E>> implements MyPriorityQueue<E>{
	public static void main(String[] args) {
		MyPQ<Integer> pq = new MyPQ<>();
		pq.add(10);
		pq.add(6);
		pq.add(11);
		pq.add(8);
		pq.add(9);
		pq.add(5);
		pq.add(7);
		System.out.println(pq.toString());
		pq.removeMin();
		System.out.println(pq.toString());
		System.out.println(pq.isEmpty());
		pq.removeMin();
		System.out.println(pq.toString());
		pq.removeMin();
		System.out.println(pq.toString());
		pq.removeMin();
		System.out.println(pq.toString());

		pq.removeMin();
		System.out.println(pq.toString());
		pq.removeMin();
		System.out.println(pq.toString());
		pq.removeMin();
		System.out.println(pq.isEmpty());
		pq.removeMin();
		System.out.println(pq.isEmpty());
	}
	
    private E[] pq;
	private int objectCount;

	public MyPQ()
	{
		this.pq = (E[])new Comparable[3];
		this.objectCount = 0;
	}

	//Returns the number of elements in the priority queue
	public int size()
	{
		return objectCount;
	}

	//DO NOT CHANGE MY JANKY TOSTRING!!!!!
	public String toString()
	{
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++)
		{
			stringbuf.append(pq[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for(int rowLength = 1, j = 0; j < objectCount; rowLength *= 2)
		{
			for (int i = 0; i < rowLength && j < objectCount; i++, j++)
			{
				stringbuf.append(pq[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount)
			{
				for (int i = 0; i < Math.min(objectCount - j, rowLength*2); i++)
				{
					if (i%2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}

	//Doubles the size of the pq array
	private void increaseCapacity()
	{
		E[] temp = (E[])new Comparable[pq.length * 2];
		for (int i = 0; i < objectCount; i++) {
			temp[i] = pq[i];
		}

		pq = temp;
	}

	//Returns the index of the "parent" of index i
	private int parent(int i)
	{
		return (i + 1) / 2 - 1;
	}
	
	//Returns the index of the *smaller child* of index i
	private int smallerChild(int i)
	{
		int candidate1 = (i + 1) * 2 - 1;
		int candidate2 = (i + 1) * 2;
		if ((candidate1 < 0 || candidate1 >= objectCount) 
			&& (candidate2 < 0 || candidate2 >= objectCount)) {
			return objectCount + 1;
		} else if (candidate1 < 0 || candidate1 >= objectCount) {
			return candidate2;
		} else if (candidate2 < 0 || candidate2 >= objectCount) {
			return candidate1;
		} else if (pq[candidate1].compareTo(pq[candidate2]) < 0) {
			return candidate1;
		} 
		
		return candidate2;
	}

	//Swaps the contents of indices i and j
	private void swap(int i, int j)
	{
		if (i >= objectCount || i < 0 || j >= objectCount || j < 0) {
			return;
		}

		E temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	// Bubbles the element at index i upwards until the pq properties hold again.
	private void bubbleUp(int i)
	{
		if (parent(i) < 0) {
			return;
		}
		
		if (pq[parent(i)].compareTo(pq[i]) > 0) {
			swap(parent(i), i);
			bubbleUp(parent(i));
		}
	}

	// Bubbles the element at index i downwards until the pq properties hold again.
	private void bubbleDown(int i)
	{
		if (smallerChild(i) >= objectCount) {
			return;
		}
		
		if (pq[smallerChild(i)].compareTo(pq[i]) < 0) {
			swap(i, smallerChild(i));
			bubbleDown(smallerChild(i));
		}
	}

	public E peek() {
		if (objectCount == 0) {
			return null;
		}

		return pq[0];
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

		E removed = pq[0];
		pq[0] = pq[objectCount - 1];
		objectCount--;
		bubbleDown(0);
		return removed;
	}

	public void add(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}

		if (objectCount == pq.length) {
			increaseCapacity();
		}

		pq[objectCount] = obj;
		objectCount++;
		bubbleUp(objectCount - 1);
	}
	
}
