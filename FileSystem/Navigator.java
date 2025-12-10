import java.util.Scanner;

/**
 * Handles interactive navigation of the file system
 * This class reads commands from standard input, interprets them,
 * and invokes operations on the current directory node.
 */
public class Navigator {

    private final FileSystemTree fileSystem;
    private FolderNode currentDirectory;
    private boolean shouldExit;

    /**
     * Constructs a navigator for a given file system tree.
     * The starting location is the root directory.
     */
    public Navigator(FileSystemTree fst) {
        this.fileSystem = fst;
        this.currentDirectory = fst.getRoot();
    }

    /**
     * Starts a command loop that repeatedly reads a line of input,
     * interprets it as a command with arguments, and executes it until
     * a request to terminate is received.
     */
    public void run() {
        shouldExit = false;
        Scanner kb = new Scanner(System.in);

        while (!shouldExit) {
            // Prompt can be customized to show current path if desired.
            String line = kb.nextLine();
            processUserInputString(line);
        }

        kb.close();
    }

    /**
     * Changes the current directory based on a single path argument.
     * Behavior should mirror typical Unix shells:
     *   - "."  refers to the current directory (no change).
     *   - ".." moves to the parent directory (if one exists).
     *   - Paths starting with "/" are interpreted from the root directory.
     *   - Other paths are interpreted relative to the current directory.
     */
    private void cd(String[] args) {
        String path = args[0];
        if (path == null) {
            throw new IllegalArgumentException();
        }

        String[] nodes = path.split("/");
        if (path.startsWith("/")) {
            currentDirectory = fileSystem.getRoot();
        }

        for (String node : nodes) {
            if (node.equals(".")) {
                continue;
            }

            if (node.equals("..")) {
                currentDirectory = (FolderNode) currentDirectory.getParent();
                continue;
            }

            currentDirectory = (FolderNode) currentDirectory.getChildByName(node);
            
        }
    }

    /**
     * Lists all items contained directly in the current directory.
     * Output formatting can mirror typical file system listings.
     */
    private void ls(String[] args) {
        for (FileSystemNode child : currentDirectory.getChildren()) {
            if (child.isFolder()) {
                System.out.println(child.getName() + "/");
            } else {
                System.out.println(child.getName());
            }
        }
    }

    /**
     * Creates a new directory inside the current directory using the provided name.
     */
    private void mkdir(String[] args) {
        String folderName = args[0];

        if (folderName == null) {
            throw new IllegalArgumentException();
        }

        currentDirectory.addFolder(folderName);
    }

    /**
     * Creates a new file inside the current directory with a given name and size.
     */
    private void touch(String[] args) {
        String fileName = args[0];
        int size = Integer.parseInt(args[1]);
        
        if (fileName == null) {
            throw new IllegalArgumentException();
        }

        currentDirectory.addFile(fileName, size);

    }

    /**
     * Searches the current directory and its descendants for nodes with a given name
     * and prints their paths.
     */
    private void find(String[] args) {
        String fileName = args[0];
        findHelper(fileName, currentDirectory);
    }

    private void findHelper(String fileName, FolderNode directory) {
        for (FileSystemNode child : directory.getChildren()) {
            if (child.getName().equals(fileName)) {
                System.out.println(child.toString());
            }

            if (child.isFolder()) {
                findHelper(fileName, (FolderNode) child);
            }
        }
    }

    /**
     * Prints the absolute path of the current directory, from the root to this node.
     */
    private void pwd(String[] args) {
        System.out.println(currentDirectory.toString());
    }

    /**
     * Displays the contents of the current directory as a tree, optionally
     * respecting flags or depth limits if provided by the arguments.
     */
    private void tree(String[] args) {
        treeHelper(currentDirectory, 1);
    }

    private void treeHelper(FolderNode directory, int depth) {
        for (int i = 0; i < directory.getChildren().size(); i++) {
            StringBuilder branchLines = new StringBuilder();
            for (int j = 0; j < depth; j++) {
                if (j == depth - 1) {
                    if (i == directory.getChildren().size() - 1) {
                        branchLines.append("|---");
                    } else {
                        branchLines.append("|---");
                    }
                } else if (j == 0) {
                    branchLines.append("|    ");
                } else {
                    branchLines.append("    ");
                }


            }

            System.out.print(branchLines);
            
            System.out.println(directory.getChildren().get(i).getName());
            if (directory.getChildren().get(i).isFolder()) {
                treeHelper((FolderNode) directory.getChildren().get(i), depth + 1);
            }
        }
    }

    /**
     * Prints how many nodes (files and folders) exist in the current directory
     * and all of its subdirectories.
     */
    private void count(String[] args) {
        System.out.println(currentDirectory.getTotalNodeCount() - 1);
    }

    /**
     * Prints the total size of all files reachable from the current directory.
     */
    private void size(String[] args) {
        System.out.println(currentDirectory.getSize());
    }

    /**
     * Prints the depth of the current directory, defined as the number of edges
     * from the root directory down to this directory.
     */
    private void depth(String[] args) {
        System.out.println(currentDirectory.getDepth());
    }

    /**
     * Prints the height of the current directory, defined as the longest downward
     * distance from this directory to any file or subdirectory beneath it.
     * An empty directory has value 0.
     */
    private void height(String[] args) {
        System.out.println(currentDirectory.getHeight());
    }

    /**
     * Signals that the interactive loop should terminate after the current command.
     */
    private void quit(String[] args) {
        shouldExit = true;
    }

    /**
     * Interprets a line of user input by splitting it into a command and arguments,
     * then forwarding control to the appropriate helper method.
     *
     * Example inputs and how they are interpreted:
     *   "ls"
     *       -> command: "ls"
     *          args: []
     *
     *   "mkdir docs"
     *       -> command: "mkdir"
     *          args: ["docs"]
     *
     *   "touch notes.txt 100"
     *       -> command: "touch"
     *          args: ["notes.txt", "100"]
     *
     *   "cd .."
     *       -> command: "cd"
     *          args: [".."]
     */
    public void processUserInputString(String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        String[] parts = line.trim().split("\\s+");
        String command = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        switch (command) {
            case "cd":
                cd(args);
                break;
            case "ls":
                ls(args);
                break;
            case "mkdir":
                mkdir(args);
                break;
            case "touch":
                touch(args);
                break;
            case "find":
                find(args);
                break;
            case "pwd":
                pwd(args);
                break;
            case "tree":
                tree(args);
                break;
            case "count":
                count(args);
                break;
            case "size":
                size(args);
                break;
            case "depth":
                depth(args);
                break;
            case "height":
                height(args);
                break;
            case "quit":
                quit(args);
                break;
            default:
                // Unknown commands can be reported back to the user.
                System.out.println("Unrecognized command: " + command);
        }
    }
}
