public class PugSaver {
	public static void main(String[] args) {
		Dog dog1 = new Dog("Bob1", "Gold Retrrgnaogin");
		Dog dog2 = new Dog("Bob2", "Golden Boaiergeg");
		Dog dog6 = new Dog("Bob6", "Pug");
		Dog dog7 = new Dog("Bob7", "Pug");
		Dog dog3 = new Dog("Bob3", "Goldie Aoerbgioaergaeg");
		Dog dog4 = new Dog("Bob4", "Pug");
		Dog dog5 = new Dog("Bob5", "Pug");
		MyArrayList<Dog> list = new MyArrayList<>();
		list.add(dog1);
		list.add(dog2);
		list.add(dog4);
		list.add(dog3);
		list.add(dog5);
		list.add(dog6);
		list.add(dog7);
		rescuePugs(list);
		System.out.println(list.toString());
	}

	// Moves every dog whose breed is "Golden" in the list to the back of the list
	public static void rescuePugs(MyArrayList<Dog> list) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (list.get(i).getBreed().contains("Gold")) {
				for (int j = size - 1; j >= 0; j--) {
					if (!list.get(j).getBreed().contains("Gold")) {
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
