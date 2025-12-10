import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Small manual tester for the solution file-system implementation.
 *
 * This does NOT use JUnit. It just runs a few operations and prints out
 * what it's doing plus the observed behavior.or
 *
 * And also assumes (based on our design):
 *  - FileSystemTree#getRoot() returns a non-null FolderNode
 *  - FolderNode has addFolder(String) and addFile(String, int) that return boolean
 *  - FileSystemNode has getDepth(), getHeight(), getSize(), getTotalNodeCount()
 *  - Navigator has processUserInputString(String) which prints results to System.out
 */
public class FileSystemTester {

    public static void main(String[] args) {

        // 1. Construct a tree and check root
        FileSystemTree tree = new FileSystemTree();
        FolderNode root = tree.getRoot();

        if (root == null) {
            System.out.println("[FAIL] Root is null. FileSystemTree.getRoot() must return a non-null root folder.");
            return;
        } else {
            System.out.println("[PASS] Root is non-null.");
        }

        System.out.println("Root toString(): " + root.toString());
        System.out.println("Expected at root: '/' (or equivalent)");

        // 2. Build a small structure under root
        System.out.println("\n=== Building tree structure under root ===");
        boolean addedDocs = root.addFolder("docs");
        boolean addedSrc = root.addFolder("src");
        boolean addedMainJava = root.addFile("main.java", 120);
        boolean addedReadme = root.addFile("README.md", 80);



        Navigator navigator = new Navigator(tree);
        navigator.processUserInputString("ls");
        navigator.processUserInputString("mkdir testFolder");
        navigator.processUserInputString("cd testFolder");
        navigator.processUserInputString("touch test.txt 100");
        navigator.processUserInputString("touch test2.txt 180");
        navigator.processUserInputString("cd ..");
        navigator.processUserInputString("tree");
        navigator.processUserInputString("find testFolder");

        System.out.println("depth: " + root.getDepth());
        System.out.println("height: " + root.getHeight());
        System.out.println("size: " + root.getSize());
        System.out.println("total node count: " + root.getTotalNodeCount());
        boolean containsName = root.containsNameRecursive("test.txt");
        System.out.println(root.toString());

        /*
        
        */

    }
}