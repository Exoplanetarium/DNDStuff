
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	public static void main(String[] args) {
		HeapPQ<Integer> heap = new HeapPQ<>();
		heap.add(10);
		heap.add(6);
		heap.add(11);
		heap.add(8);
		heap.add(9);
		heap.add(5);
		heap.add(7);
		System.out.println(heap.toString());
		heap.removeMin();
		System.out.println(heap.toString());
		System.out.println(heap.isEmpty());
		heap.removeMin();
		System.out.println(heap.toString());
		heap.removeMin();
		System.out.println(heap.toString());
		heap.removeMin();
		System.out.println(heap.toString());

		heap.removeMin();
		heap.removeMin();
		heap.removeMin();
		System.out.println(heap.isEmpty());
		heap.removeMin();
	}

	private E[] heap;
	private int objectCount;

	public HeapPQ()
	{
		this.heap = (E[])new Comparable[3];
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
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for(int rowLength = 1, j = 0; j < objectCount; rowLength *= 2)
		{
			for (int i = 0; i < rowLength && j < objectCount; i++, j++)
			{
				stringbuf.append(heap[j] + " ");
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

	//Doubles the size of the heap array
	private void increaseCapacity()
	{
		E[] temp = (E[])new Comparable[heap.length * 2];
		for (int i = 0; i < objectCount; i++) {
			temp[i] = heap[i];
		}

		heap = temp;
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
		} else if (heap[candidate1].compareTo(heap[candidate2]) < 0) {
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

		E temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i)
	{
		if (parent(i) < 0) {
			return;
		}
		
		if (heap[parent(i)].compareTo(heap[i]) > 0) {
			swap(parent(i), i);
			bubbleUp(parent(i));
		}
	}

	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i)
	{
		if (smallerChild(i) >= objectCount) {
			return;
		}
		
		if (heap[smallerChild(i)].compareTo(heap[i]) < 0) {
			swap(i, smallerChild(i));
			bubbleDown(smallerChild(i));
		}
	}

	public E peek() {
		if (objectCount == 0) {
			return null;
		}

		return heap[0];
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

		E removed = heap[0];
		heap[0] = heap[objectCount - 1];
		objectCount--;
		bubbleDown(0);
		return removed;
	}

	public void add(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}

		if (objectCount == heap.length) {
			increaseCapacity();
		}

		heap[objectCount] = obj;
		objectCount++;
		bubbleUp(objectCount - 1);
	}

}
