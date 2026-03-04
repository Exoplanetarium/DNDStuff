import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPredictor {

    // Please define your own variables and data structures
    // 
    private HashMap<String, ArrayList<String>> markov;

    public MarkovPredictor(String filePath) {
        this.markov = new HashMap<String, ArrayList<String>>(10);
        ArrayList<String[]> lines = readData(filePath);
        populateHashMap(lines);
    }

    public ArrayList<String[]> readData(String filePath) {
        ArrayList<String[]> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                arr.add(str);
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void populateHashMap(ArrayList<String[]> arr) {
        ArrayList<String> values = new ArrayList<>();
        for (String[] str : arr) {
            if (!markov.containsKey(str[0])) {
                values.add(str[1]);
                markov.put(str[0], values);
            } else {
                values = markov.get(str[0]);
                values.add(str[1]);
                markov.put(str[0], values);
            }

            values = new ArrayList<>();
        }
    }


    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {
        for (String key : markov.keySet()) {
            if (key.equals(currentState)) {
                ArrayList<String> values = markov.get(key);
                int rand = (int) Math.floor(Math.random() * values.size());
                return values.get(rand);
            }
        }

        return null;
    }

}