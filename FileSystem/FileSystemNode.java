/**
 * Represents a node in a file system tree.
 * Both folders and files share a name and a reference to their parent folder.
 */
public abstract class FileSystemNode {

    private String name;
    private FolderNode parent;

    public FileSystemNode(String name, FolderNode parent) {
        this.name = name;
        this.parent = parent;
    }

    /**
     * Returns the folder that directly contains this node,
     * or null if this is the root.
     */
    public FolderNode getParent() {
        return parent;
    }

    /**
     * Returns the simple name of this node (no path).
     */
    public String getName() {
        return name;
    }

    /**
     * Indicates whether this node represents a folder.
     * Implementations in subclasses should return true for folders and false for files.
     */
    public abstract boolean isFolder();

    /**
     * Computes the number of edges from the root node down to this node.
     * The root has value 0, its children have value 1, and so on.
     */
    public int getDepth() {
        int count = 0;
        FileSystemNode current = this;
        while (current.getParent() != null) {
            current = current.getParent();
            count++;
        }

        return count;
    }

    /**
     * Computes the longest downward distance from this node to any descendant node.
     * Leaf nodes (files or empty folders) should have value 0.
     */
    public abstract int getHeight();

    /**
     * Computes the total size contributed by this node and all descendants.
     * For a file this is its own size; for a folder this is the sum of all contained files.
     */
    public abstract int getSize();

    /**
     * Counts how many nodes are included when starting at this node
     * and following all child links recursively, including this node itself.
     */
    public abstract int getTotalNodeCount();

    /**
     * Builds a full path for this node from the root, using "/" as a separator.
     * Folders may choose to represent themselves with a trailing "/" if desired.
     *  "root/folder/subfolder/file"
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("/");
        FileSystemNode current = this;
        while (current.getParent() != null) {
            str.insert(0, "/" + current.getName());
            current = current.getParent();
        }

        if (this.getParent() != null) {
            str.delete(str.length() - 1, str.length());
        }
        
        return str.toString();
    }
}
