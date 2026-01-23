public class MyStack<E> {

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<String>();
        stack.push("Derp");
        stack.push("Derp");
        System.out.println(stack.empty());
        String str = stack.peek();
        str = stack.pop();
        System.out.println(stack.empty());
    }

    /* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E [] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyStack() {
		this.internalArray = (E[])new Object[100];
	}

    @SuppressWarnings("unchecked")
	public MyStack(int initialCapacity){
		this.internalArray = (E[])new Object[initialCapacity];
	}

    public boolean empty() {
        if (objectCount == 0) {
            return true;
        }

        return false;
	}

    public E peek() {
        if (objectCount == 0) {
            return null;
        }

		return internalArray[internalArray.length - 1];
	}

    @SuppressWarnings("unchecked")
    public boolean push(E obj) {
        int size = internalArray.length;
        if (objectCount + 1 >= size) {
            E[] newArr = (E[])new Object[size * 2];
            for (int i = 0; i < size; i++) {
                newArr[i] = internalArray[i];
            }
			newArr[size] = obj;
            internalArray = newArr;
        } 

        internalArray[objectCount] = obj;
        objectCount++;
        return true;
    }

    public E pop() {
        if (objectCount == 0) {
            return null;
        }

        E removed = internalArray[objectCount];
        internalArray[objectCount] = null;
        objectCount--;
        return removed;
    }



}