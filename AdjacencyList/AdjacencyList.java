import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


public class AdjacencyList {
    private HashMap<String, List<String>> adjList;
    private int objCount;

    public AdjacencyList() {
        this.adjList = new HashMap<String, List<String>>(30);
        this.objCount = 0;
    }

    public void fillList(List<String> values) {

        List<String> value = new ArrayList<>();
        String key = "";
        for (String val : values) {
            String[] people = val.split(",");
            if (!people[0].equals(key)) {
                key = people[0];
            }
            value.add(val);
            adjList.put(key, value);
        }
    }

    public static List<String> readAllLines(Path file) throws IOException {
        // Each element is one line from the file
        return Files.readAllLines(file);
    }
}