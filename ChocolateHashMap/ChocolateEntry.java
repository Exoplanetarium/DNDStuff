/**
 * ChocolateEntry models one row in a chocolate-factory ledger:
 * a key (e.g., SKU, lot code, recipe name) mapped to a value (e.g., batch info).
 *
 * This is a lightweight "pair" class intended to be stored inside ChocolateHashMap buckets.
 */
public class ChocolateEntry<K, V> {
    private final K key;
    private V value;

    public ChocolateEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChocolateEntry<?, ?>)) return false;

        ChocolateEntry<?, ?> other = (ChocolateEntry<?, ?>) o;

        if (this.key == null) {
            if (other.key != null) return false;
        } else if (!this.key.equals(other.key)) {
            return false;
        }

        if (this.value == null) {
            return other.value == null;
        }
        return this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        int result = (key == null) ? 0 : key.hashCode();
        result = 31 * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(key) + "," + String.valueOf(value);
    }
}
