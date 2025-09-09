/* See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E [] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.internalArray = (E[])new Object[100];
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity){
		this.internalArray = (E[])new Object[initialCapacity];
	}

	/* Return the number of active slots in the array list */
	// should be O(1)
	public int size() {
		/* ---- YOUR CODE HERE ---- */
        return objectCount;
	}

    /* Are there zero objects in the array list? */
	// should be O(1)
	public boolean isEmpty() {
		/* ---- YOUR CODE HERE ---- */
		if (objectCount == 0) {
			return true;
		}

		return false;
	}

	/* Get the index-th object in the list. */
	// should be O(1)
	public E get(int index) {
		/* ---- YOUR CODE HERE ---- */
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

        // if (internalArray[index] == null) {
		// 	throw new IndexOutOfBoundsException();
		// }

		return internalArray[index];
	}

	/* Replace the object at index with obj.  returns object that was replaced. */
	// should be O(1)
	public E set(int index, E obj) {
		/* ---- YOUR CODE HERE ---- */
		if (index < 0 || index >= internalArray.length) {
			throw new IndexOutOfBoundsException();
		}

        E temp = internalArray[index];
        internalArray[index] = obj;
        return temp;
	}

	/* Returns true if this list contains an element equal to obj;
	 otherwise returns false. */
	// should be O(n)
	public boolean contains(E obj) {
		/* ---- YOUR CODE HERE ---- */
		for (int i = 0; i < internalArray.length; i++) {
			if (internalArray[i].equals(obj)) {
				return true;
			}
		}

		return false;
	}

	/* Insert an object at index */
	// should be O(n)
	@SuppressWarnings("unchecked")
	public void add(int index, E obj) {
		/* ---- YOUR CODE HERE ---- */
		if (index < 0 || index > internalArray.length) {
			throw new IndexOutOfBoundsException();
		}

        int size = internalArray.length;
        if (size() + 1 >= size) {
            E[] newArr = (E[])new Object[size * 2];
            for (int i = 0; i < size; i++) {
                newArr[i] = internalArray[i];
            }
			newArr[size] = obj;
            internalArray = newArr;
        } 

		for (int i = size() - 1; i >= index; i--) {
			internalArray[i + 1] = internalArray[i];
		}

		internalArray[index] = obj;
		objectCount++;
	}

	/* Add an object to the end of the list; returns true */
	// should be O(1)
	public boolean add(E obj) {
		/* ---- YOUR CODE HERE ---- */
        add(size(), obj);
        return true;
	}

	/* Remove the object at index and shift.  Returns removed object. */
	// should be O(n)
	public E remove(int index) {
		/* ---- YOUR CODE HERE ---- */
		if (index < 0 || index >= objectCount) {
			throw new IndexOutOfBoundsException();
		}

		E removed = internalArray[index];
		for (int i = index; i < internalArray.length - 1; i++) {
			internalArray[i] = internalArray[i + 1];
		}

		internalArray[internalArray.length - 1] = null;

		objectCount--;
		return removed;
	}

	/* Removes the first occurrence of the specified element from this list, 
	 * if it is present. If the list does not contain the element, it is unchanged. 
	 * More formally, removes the element with the lowest index i such that
	 * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
	 * Returns true if this list contained the specified element (or equivalently, 
	 * if this list changed as a result of the call). */
	// should be O(n)
	public boolean remove(E obj) {
		/* ---- YOUR CODE HERE ---- */
		int index = 0;
		while (index < internalArray.length) {
			if (obj == null ? get(index) == null : obj.equals(get(index))) {
				break;
			}
			index++;
		}

		if (index == internalArray.length) {
			return false;
		}

		remove(index);
		return true;
	}


	/* For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are the elements in the ArrayList.
	 * If the array is empty, it should return "[]".  If there is one element, "[X]", etc.
	 * Elements are separated by a comma and a space. */
	// should be O(n)
	public String toString() {
		/* ---- YOUR CODE HERE ---- */
		StringBuilder arr = new StringBuilder("[");
        for (int i = 0; i < size() - 1; i++) {
			if (internalArray[i] == null) {
				arr.append("null, ");
				continue;
			}
			
            arr.append(internalArray[i].toString());
            arr.append(", ");
        }

        arr.append(internalArray[size() - 1].toString());
		arr.append("]");
        return arr.toString();
	}

}