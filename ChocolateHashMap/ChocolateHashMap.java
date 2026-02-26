/**
 * ChocolateHashMap<K,V>
 *
 * A custom hash map (separate chaining) built for a fictional chocolate factory inventory system.
 * Each bucket is a circular DOUBLY-linked list with a sentinel BatchNode.
 *
 * You are responsible for implementing the methods marked TODO.
 */
public class ChocolateHashMap<K, V> {
    private BatchNode<ChocolateEntry<K, V>>[] buckets;
    private int objectCount;
    private double loadFactorLimit;

    // Constructor: creates a hash map with the given initial bucket size and load factor limit
    @SuppressWarnings("unchecked")
    public ChocolateHashMap(int bucketCount, double loadFactorLimit) {
        this.buckets = (BatchNode<ChocolateEntry<K, V>>[]) new BatchNode[bucketCount];
        fillArrayWithSentinels(buckets);
        this.objectCount = 0;
        this.loadFactorLimit = loadFactorLimit;
    }

    // Constructor: creates an empty hash map with default parameters
    public ChocolateHashMap() {
        this(10, 0.75);
    }

    private static void fillArrayWithSentinels(BatchNode[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new BatchNode();
        }
    }

    // Return a pointer to the bucket array
    public BatchNode<ChocolateEntry<K, V>>[] getBuckets() {
        return this.buckets;
    }

    // Returns true if this map is empty; otherwise returns false.
    public boolean isEmpty() {
        return (objectCount == 0);
    }

    // Returns the number of entries in this map.
    public int size() {
        return objectCount;
    }

    // Return the bucket index for the key
    // Use .hashCode(), but be aware that hashCode can return negative numbers!
    // NOTE: Math.abs(Integer.MIN_VALUE) is still negative. Consider masking the sign bit.
    private int whichBucket(K key) {
        long index = key.hashCode();
        if (index < 0) {
            long longIndex = index;
            index = -longIndex;
        }

        return (int) (index % buckets.length);
    }

    // Returns the current load factor (objCount / buckets)
    public double currentLoadFactor() {
        return ((double) objectCount) / ((double) buckets.length);
    }

    // Return true if the key exists as a key in the map, otherwise false.
    // Use the .equals method to check equality.
    public boolean containsKey(K key) {
        for (BatchNode<ChocolateEntry<K, V>> node : buckets) {    
            node = node.getNext();       
            while (!node.isSentinel()) {
                if (node.getEntry().getKey().equals(key)) {
                    return true;
                }

                node = node.getNext();
            }
        }

        return false;
    }

    // Return true if the value exists as a value in the map, otherwise false.
    // Use the .equals method to check equality.
    public boolean containsValue(V value) {
        for (BatchNode<ChocolateEntry<K, V>> node : buckets) {
            node = node.getNext();
            while (!node.isSentinel()) {
                if (node.getEntry().getValue() == null) {
                    if (value == null) {
                        return true;
                    }
                } else if (node.getEntry().getValue().equals(value)) {
                    return true;
                }

                node = node.getNext();
            }
        }

        return false;
    }

    // Puts a key-value pair into the map.
    // If the key already exists in the map you should *not* add the key-value pair.
    // Return true if the pair was added; false if the key already exists.
    // If a pair should be added, add it to the END of the bucket.
    // After adding the pair, check if the load factor is greater than the limit.
    // - If so, you must call rehash with double the current bucket size.
    public boolean put(K key, V value) {
        if (containsKey(key)) {
            return false;
        }   

        ChocolateEntry<K, V> pair = new ChocolateEntry<K, V>(key, value);
        BatchNode<ChocolateEntry<K, V>> newNode = new BatchNode<ChocolateEntry<K, V>>(pair);
        BatchNode<ChocolateEntry<K, V>> node = buckets[whichBucket(key)];
        node = node.getPrevious();
        node.setNext(newNode);
        newNode.setPrevious(node);
        buckets[whichBucket(key)].setPrevious(newNode);
        newNode.setNext(buckets[whichBucket(key)]);

        objectCount++;

        if (currentLoadFactor() > loadFactorLimit) {
            System.out.println("triggered");
            rehash(buckets.length * 2);
        }

        return true;
    }

    // Returns the value associated with the key in the map.
    // If the key is not in the map, then return null.
    public V get(K key) {
        BatchNode<ChocolateEntry<K, V>> node = buckets[whichBucket(key)];
        node = node.getNext(); 
        while (!node.isSentinel()) {
            if (node.getEntry() == null) {
                return null;
            }

            if (node.getEntry().getKey().equals(key)) {
                return node.getEntry().getValue();
            }

            node = node.getNext();
        }

        return null;
    }

    // Remove the pair associated with the key.
    // Return true if successful, false if the key did not exist.
    public boolean remove(K key) {
        if (!containsKey(key)) {
            return false;
        }

        BatchNode<ChocolateEntry<K, V>> node = buckets[whichBucket(key)];
        node = node.getNext();
        while (!node.isSentinel()) {
            if (node.getEntry().getKey().equals(key)) {
                BatchNode<ChocolateEntry<K, V>> prev = node.getPrevious();
                prev.setNext(node.getNext());
                node.getNext().setPrevious(prev);
                objectCount--;
                return true;
            }

            node = node.getNext();
        }

        return false;
    }

    // Rehash the map so that it contains the given number of buckets
    // Loop through all existing buckets, from 0 to length
    // Rehash each object into the new bucket array in the order they appear on the original chain.
    // I.e. if a bucket originally has (sentinel)->J->Z->K, then J will be rehashed first,
    // followed by Z, then K.
    public void rehash(int newBucketCount) {
        BatchNode<ChocolateEntry<K, V>>[] bucketCopy = buckets;
        BatchNode<ChocolateEntry<K, V>>[] newMap = (BatchNode<ChocolateEntry<K, V>>[]) new BatchNode[newBucketCount];
        fillArrayWithSentinels(newMap);
        buckets = newMap;
        objectCount = 0;
        for (BatchNode<ChocolateEntry<K, V>> node : bucketCopy) {
            node = node.getNext();
            while (!node.isSentinel()) {
                put(node.getEntry().getKey(), node.getEntry().getValue());
                node = node.getNext();
            }
        }
    }

    // The output should be in the following format:
    // [ n, k | { b#: k1,v1 k2,v2 k3,v3 } { b#: k1,v1 k2,v2 } ]
    // n is the objCount
    // k is the number of buckets
    // For each bucket that contains objects, create a substring that indicates the bucket index
    // And list all of the items in the bucket (in the order they appear)
    // Example (using chocolate-themed data):
    // [ 3, 10 | { b3: LOT-70,DARK LOT-12,MILK } { b7: LOT-99,WHITE } ]
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[" + objectCount + ", " + buckets.length + " | ");
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].getNext().isSentinel()) {
                continue;
            }

            str.append("{ b" + i + ": ");
            BatchNode<ChocolateEntry<K ,V>> node = buckets[i];
            node = node.getNext();
            while (!node.isSentinel()) {
                str.append(node.getEntry().getKey() + "," + node.getEntry().getValue() + " ");
                node = node.getNext();
            }
            str.append("} ");
        }

        str.append("]");
        return str.toString();
    }
}
