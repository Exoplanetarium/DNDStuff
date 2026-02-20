import java.util.ArrayList;

public class HashFunction {
    public static void main(String[] args) {
        String[] output = new String[500];
        String[] names = {
            "Mateo Atluri",
            "Asher Butan",
            "Xander Cheuk",
            "Taj Clement",
            "Camille Condren",
            "Evan Daneshrad",
            "Felicia Duan",
            "Jake Effress",
            "Zachary Figlin",
            "James Graczyk",
            "David Hadi",
            "Quinn Harris",
            "Jackson Hubbard",
            "Siona Kirschner",
            "Dylan Martin",
            "Morgan Maynard",
            "Yari Milakin",
            "Waller Morton",
            "Andrew Stout",
            "Mattin Tasbihgoo",
            "Andrew Theiss",
            "Carter Tsao",
            "Rose Ananda",
            "Autrin Anousheh",
            "Joshua Bie",
            "Elsa Cheng",
            "Isabel Erlic",
            "Jojo Gott",
            "Connor Jun",
            "Jordan Kay",
            "James Klarin",
            "Judy Law",
            "Grayden Lichtman",
            "Runshi Liu",
            "Juan Lopez",
            "Henry Margolis",
            "Garret Morberg-Nguyen",
            "Kai Nantamanasikarn",
            "Remi O'Dell",
            "Emil Palmer",
            "Jaden Park",
            "Ryder Rufi",
            "Alice Shao",
            "Marco Silvera",
            "Samuel Tabib",
            "Andrew Theiss",
            "Shriya Vishwas",
            "Nick Waller",
            "Vikram Wright",
            "Alex Yang",
            "Ethan You",
            "Lucas Yu",
            "Jack Yuan",
            "Jayden Zepeda",
            "Lawrence Zhao",
            "Michael Zhao",
            "Olivia Zhu"
        };

        for (int i = 0; i < names.length; i++) {
            int nextIndex = hashFunction(names[i]);
            if (output[nextIndex] != null) {
                System.out.println("duplicateFound for name: " + names[i] + " at index:" + nextIndex);
            }
            output[nextIndex] = names[i];
        }
    }

    public HashFunction() {

    }

    public static int hashFunction(String str) {
        char[] chars = str.toCharArray();
        String sum = "";
        for (char c : chars) {
            sum += (int) (Math.pow(c, 2) - c - 199);
        }

        return (int) (((Double.parseDouble(sum) + 9) / 1231231244 + Double.parseDouble(sum) * (1 / 131123441) - 3 * Math.pow(17, 6)) % 219539285 % 9571935 % 79972 % 500);
    }
}