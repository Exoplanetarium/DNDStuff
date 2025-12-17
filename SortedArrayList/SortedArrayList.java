public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E>{
	public static void main(String[] args) {
		SortedArrayList<String> arr = new SortedArrayList<>();
		arr.add("homosexual");
		arr.add("mongolian");
		arr.add("attack");
		arr.add("helicopter");
		arr.add("helicopter");
		arr.add("yello");
		arr.add(null);
		arr.add(null);

		System.out.println("contains null: " + arr.contains(null));
		System.out.println("contains attack: " + arr.contains("attack"));
		System.out.println("contains coffee: " + arr.contains("coffee"));
		
		arr.remove(null);
		arr.remove("homosexual");
		arr.remove("coffee");

		System.out.println("min: " + arr.min().toString());
		System.out.println("max: " + arr.max().toString());
	}
	
	@Override
	public boolean contains(E obj) {
		return containsHelper(this, obj, 0, size());
	}

	public boolean containsHelper(MyArrayList<E> arr, E key, int low, int high) {
		int mid = (low + high) / 2;

		if (arr.get(size() - 1) == null && key == null) {
			return true;
		}

		if (high - low == 1) {
			if (arr.get(low).compareTo(key) != 0) {
				return false;
			}
		}

        if (arr.get(mid).compareTo(key) > 0) {
            return containsHelper(arr, key, low, mid);
        } else if (arr.get(mid).compareTo(key) < 0) {
            return containsHelper(arr, key, mid, high);
        } else if (arr.get(mid).compareTo(key) == 0) {
            return true;
        } else {
			return false;
		}
	}
	
	// May not contain more than one of the same object
	@Override
	public boolean add(E obj) {
		if (obj == null && this.get(size() - 1) != null) {
			return super.add(obj);
		} else if (obj == null) {
			return false;
		}

		for (int i = 0; i < size(); i++) {
			if (this.get(i) == null) {
				super.add(i, obj);
				return true;
			}

			if (this.get(i).compareTo(obj) == 0) {
				return false;
			}

			if (this.get(i).compareTo(obj) > 0) {
				super.add(i, obj);
				return true;
			}
		}

		return super.add(obj);
	}
	
	@Override
	public boolean remove(E obj) {
		return super.remove(obj);
	}
	
	public E min() {
		E minObject = null;
		for (int i = 0; i < size(); i++) {
			if (this.get(i) == null) {
				continue;
			}

			if (i == 0) {
				minObject = this.get(i);
			}

			if (this.get(i).compareTo(minObject) < 0) {
				minObject = this.get(i);
			}
		}

		return minObject;
	}
	
	public E max() {
		E maxObject = null;
		for (int i = 0; i < size(); i++) {
			if (this.get(i) == null) {
				continue;
			}

			if (i == 0) {
				maxObject = this.get(i);
			}

			if (this.get(i).compareTo(maxObject) > 0) {
				maxObject = this.get(i);
			}
		}

		return maxObject;
	}
}
