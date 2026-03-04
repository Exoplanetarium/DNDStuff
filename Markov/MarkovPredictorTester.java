public class MarkovPredictorTester {
    public static void main(String[] args) {
        MarkovPredictor test = new MarkovPredictor("./weather.csv");
        for (int i = 0; i < 100; i++) {
            System.out.println(test.predictNextState("Rainy"));
        }
    }
}
