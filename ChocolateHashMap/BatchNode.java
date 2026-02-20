/**
 * Represents a node of a circular DOUBLY-linked list.
 *
 * In ChocolateHashMap, each bucket is a circular doubly-linked list that begins with
 * a sentinel BatchNode. For a sentinel node:
 *   - entry == null
 *   - previous points to itself
 *   - next points to itself
 *
 * Non-sentinel nodes hold an actual entry.
 */
public class BatchNode<E> {
    private E entry;
    private BatchNode<E> previous;
    private BatchNode<E> next;
    private final boolean isSentinel;

    public BatchNode(E entry, BatchNode<E> previous, BatchNode<E> next) {
        this.entry = entry;
        this.previous = previous;
        this.next = next;
        this.isSentinel = false;
    }

    public BatchNode(E entry) {
        this(entry, null, null);
    }

    // Sentinel constructor
    public BatchNode() {
        this.entry = null;
        this.previous = this;
        this.next = this;
        this.isSentinel = true;
    }

    public E getEntry() { return entry; }
    public BatchNode<E> getPrevious() { return previous; }
    public BatchNode<E> getNext() { return next; }
    public boolean isSentinel() { return isSentinel; }

    public void setEntry(E entry) { this.entry = entry; }
    public void setPrevious(BatchNode<E> previous) { this.previous = previous; }
    public void setNext(BatchNode<E> next) { this.next = next; }

    // --- Optional helper operations ---
    // These helpers are here so you can focus on hashing logic instead of pointer bookkeeping.

    /**
     * Inserts newNode immediately BEFORE this node.
     * Example: sentinel.insertBefore(newNode) appends to the end of the list.
     */
    public void insertBefore(BatchNode<E> newNode) {
        BatchNode<E> prev = this.previous;
        newNode.previous = prev;
        newNode.next = this;
        prev.next = newNode;
        this.previous = newNode;
    }

    /**
     * Removes this node from its list (no-op if this node is a sentinel).
     */
    public void unlink() {
        if (this.isSentinel) return;

        BatchNode<E> prev = this.previous;
        BatchNode<E> nxt = this.next;

        prev.next = nxt;
        nxt.previous = prev;

        // Clear pointers to help debugging accidental reuse
        this.previous = null;
        this.next = null;
    }
}