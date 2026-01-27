import java.io.*;
import java.util.*;

// You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make stacks and queues
import java.util.Stack;
import java.util.ArrayDeque;

public class CookieMonster {

	public static void main(String[] args) {
		CookieMonster bob = new CookieMonster("cookies0.txt");
		System.out.println(bob.recursiveCookies());
	}

    private int [][] cookieGrid;
    private int numRows;
    private int numCols;
    
    //Constructs a CookieMonster from a file with format:
    //numRows numCols
    //<<rest of the grid, with spaces in between the numbers>>
    public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try
		{
			Scanner input = new Scanner(new File(fileName));

			numRows    = input.nextInt();  
			numCols    = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++) 
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();
			
			input.close();
		}
		catch (Exception e)
		{
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

    }

    public CookieMonster(int [][] cookieGrid) {
        this.cookieGrid = cookieGrid;
        this.numRows    = cookieGrid.length;
        this.numCols    = cookieGrid[0].length;
    }

    //You may find it VERY helpful to write this helper method.  Or not!
	private boolean validPoint(int row, int col) {
		if (cookieGrid[row][col] >= 0) {
			return true;
		}

		return false;
	}
	
	/* RECURSIVELY calculates the route which grants the most cookies.
	 * Returns the maximum number of cookies attainable. */
	public int recursiveCookies() {
		return recursiveCookies(0,0);	
	}	
	
	// Returns the maximum number of cookies edible starting from (and including) cookieGrid[row][col]
	public int recursiveCookies(int row, int col) {
		if (row == cookieGrid.length || col == cookieGrid[0].length) {
			return -1;
		}

		if (!validPoint(row, col)) {
			return -1;
		} 

		int down = recursiveCookies(row, col + 1);
		int right = recursiveCookies(row + 1, col);
		if (down == right && down == -1) {
			return cookieGrid[row][col];
		} else if (down > right) {
			return down + cookieGrid[row][col];
		} else {
			return right + cookieGrid[row][col];
		}
	}
	

	/* Calculate which route grants the most cookies using a QUEUE.
	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int queueCookies() {
		int max = 0;
		Queue<OrphanScout> queue = new ArrayDeque<>();
		int row = 0;
		int col = 0;
		queue.offer(new OrphanScout(row, col, 0));
		while (row < cookieGrid.length && col < cookieGrid[0].length) {
			while (!queue.isEmpty()) {
				OrphanScout disposable = queue.poll();
				int nextRow = disposable.getEndingRow();
				int nextCol = disposable.getEndingCol();
				if (cookieGrid[nextRow][nextCol] != -1) {
					queue.offer(new OrphanScout(row + 1, col, max + cookieGrid[nextRow][nextCol]));
					queue.offer(new OrphanScout(row, col + 1, max + cookieGrid[nextRow][nextCol]));
				}
			}
			
		}
		
		return max;
    }

    
    /* Calculate which route grants the most cookies using a stack.
 	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int stackCookies() {
		//CODE THIS
		return 0;
    }

}
