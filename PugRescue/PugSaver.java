import java.util.ArrayList;

public class PugSaver {
	public static void main(String[] args) {
		MyArrayList<Dog> list = new MyArrayList<>(1000000);
		for (int i = 0; i < 500000; i++) {
			list.add(new Dog("Lady", "Golden"));
		}

		for (int i = 0; i < 500000; i++) {
			list.add(new Dog("Paco", "Mutt"));
		}

		rescuePugs(list);
		System.out.println(list.toString());
	}

	// Moves every dog whose breed is "Golden" in the list to the back of the list
	public static void rescuePugs(ArrayList<Dog> list) {
		int size = list.size();
		for (int i = list.size() - size; i < size; i++) {
			if (list.get(i).getBreed().contains("Gold")) {
				for (int j = size - 1; j >= list.size() - size; j--) {
					if (i == j) {
						break;
					}
					
					if (!list.get(j).getBreed().contains("Gold")) {
						if (i + j > list.size()) {
							size--;
							break;
						}

						Dog temp = list.set(j, list.get(i));
						list.set(i, temp);
						size--;
						break;
					}
				}
			}
		}
	}

	// Moves every dog whose breed is "Golden" in the list to the back of the list
	public static void rescuePugs(MyArrayList<Dog> list) {
		int size = list.size();
		for (int i = list.size() - size; i < size; i++) {
			if (list.get(i).getBreed().contains("Gold")) {
				for (int j = size - 1; j >= list.size() - size; j--) {
					if (i == j) {
						break;
					}
					
					if (!list.get(j).getBreed().contains("Gold")) {
						if (i + j > list.size()) {
							size--;
							break;
						}

						Dog temp = list.set(j, list.get(i));
						list.set(i, temp);
						size--;
						break;
					}
				}
			}
		}
	}
}
