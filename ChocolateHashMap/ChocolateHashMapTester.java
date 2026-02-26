public class ChocolateHashMapTester {
    public static void main(String[] args) {
        ChocolateHashMap map = new ChocolateHashMap<>();
        map.put("Key", "entry");
        map.put("Bob", "entry");
        map.put("Yes", "Bob");
        map.put("Yes", "Bob");
        map.put("Yes2", "Bob2");
        map.put("Yes3", "Bob3");
        map.put("Yes4", "Bob4");
        map.put("Yes5", "Bob5");
        map.put("Yes6", "Bob6");

        System.out.println(map.currentLoadFactor());

        map.get("Key");
        map.containsKey("Key");
        map.containsValue("no");
        map.remove("Key");
        map.currentLoadFactor();
        map.rehash(50);

        System.out.println(map.getBuckets().length);
        System.out.println(map.toString());

    }
}
