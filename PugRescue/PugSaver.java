import java.util.ArrayList;

public class PugSaver {
	public static void main(String[] args) {
		ArrayList<Dog> list = new ArrayList<>();
		list.add(new Dog("Lady", "Golden"));
		list.add(new Dog("Paco", "Mutt"));
		list.add(new Dog("Fifi", "Mutt"));
		list.add(new Dog("Hector", new String("Gol"+"den")));
		list.add(new Dog("Luna", "Golden"));
		list.add(new Dog("Rosie", "Labradoodle"));
		list.add(new Dog("Liberty", "German Shepherd"));
		list.add(new Dog("Monchy", "Golden"));
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
}
