import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system tree.
 * A directory can contain other directories and files as children.
 */
public class FolderNode extends FileSystemNode {

    private List<FileSystemNode> children;

    public FolderNode(String name, FolderNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }


    @Override
    public boolean isFolder() {
        return true;
    }

    /**
     * Returns a list view of the children contained directly inside this directory.
     * Modifying the returned list is not required to be supported.
     */
    public List<FileSystemNode> getChildren() {
        return children;
    }

    /**
     * Searches the children of this directory for a node whose name matches the input.
     * Only direct children are considered, not deeper descendants.
     */
    public FileSystemNode getChildByName(String childName) {
        for (FileSystemNode child : children) {
            if (child.equals(null) && childName == null) {
                return null;
            }
            if (child.getName().equals(childName)) {
                return child;
            }
        }

        return null;
    }

    /**
     * Creates a new file directly inside this directory with the given name and size.
     * If a child with the same name already exists, no file is created and false is returned.
     * Otherwise the new file is added and true is returned.
     */
    public boolean addFile(String fileName, int size) {
        if (getChildByName(fileName) == null) {
            FileNode newFile = new FileNode(this, fileName, size);
            children.add(newFile);
            return true;
        }

        return false;
    }

    /**
     * Creates a new subdirectory directly inside this directory with the given name.
     * If a child with the same name already exists, no folder is created and false is returned.
     * Otherwise the new folder is added and true is returned.
     */
    public boolean addFolder(String folderName) {
        if (getChildByName(folderName) == null) {
            FolderNode newFolder = new FolderNode(folderName, this);
            children.add(newFolder);
            return true;
        }

        return false;
    }

    /**
     * Searches this directory and all of its descendants for nodes whose name matches the input.
     * When a match is found, its full path can be printed by the caller using toString().
     */
    public boolean containsNameRecursive(String searchName) {
        for (FileSystemNode child : children) {
            if (child.isFolder()) {
                containsNameRecursive(child.getName());
            }

            if (child.equals(null) && searchName == null) {
                return true;
            }
            if (child.getName().equals(searchName)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int getHeight() {
        return getHeightHelper(this);
    }

    public int getHeightHelper(FileSystemNode current) {
        if (!current.isFolder()) {
            return 0;
        }

        int maxCount = 0;
        for (FileSystemNode child : children) {
            int count = 0;
            if (child.isFolder()) {
                count += getHeightHelper(child);
            }

            if (count > maxCount) {
                maxCount = count;
            }
        }

        return maxCount;
    }

    @Override
    public int getSize() {
        return getSizeHelper(this);
    }

    public int getSizeHelper(FileSystemNode current) {
        if (!current.isFolder()) {
            return current.getSize();
        }

        int size = 0;
        for (FileSystemNode child : children) {
            if (child.isFolder()) {
                size += getSizeHelper(child);
            }

        }

        return size;
    }

    @Override
    public int getTotalNodeCount() {
        return getTotalNodeCountHelper(this);
    }

    public int getTotalNodeCountHelper(FileSystemNode current) {
        if (!current.isFolder()) {
            return 1;
        }

        int nodeCount = 0;
        for (FileSystemNode child : children) {
            if (child.isFolder()) {
                nodeCount += getSizeHelper(child);
            }

        }

        return nodeCount;
    }
}
