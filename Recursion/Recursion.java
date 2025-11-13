import java.util.ArrayList;

public class Recursion {
	public static void main(String[] args) {
		ListNode head = new ListNode("Hi");
		ListNode node = new ListNode("Cya");
		ListNode tail = new ListNode("Bye");
		head.setNext(node);
		node.setNext(tail);

		printListInReverse(head);

		String[][] grid = {{"alive", "alive", "alive", "vaccinated"}, {"alive", "vaccinated", "alive", "alive"}};
		infect(grid, 1, 2);

		System.out.println("non consec: " + countNonConsecutiveSubsets(5));
		System.out.println("ways to jump stairs: " + countWaysToJumpUpStairs(5));

		String abc = "abc";
		printSubsets(abc);
		printPermutations(abc);
		int[] sorted = {11, 5, 8, 3, 10, 2};
		mergeSort(sorted);
		int[] sorted2 = {4, 12, 8, 0, 3, 1};
		quickSort(sorted2);

		solveHanoi(3);
		int[] times = {3, 7, 9};
		int[] points = {10, 15, 10};
		System.out.println("Best reward: " + scavHunt(times, points));
	}

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
		if (head.getNext() == null) {
			System.out.println(head.getValue());
			return;
		}
		
		Object value = head.getValue();
		printListInReverse(head.getNext());
		if (value == null) {
			System.out.println("null");
		} else {
			System.out.println(value);
		}
	}

	// For the given 2D array of Strings, replaces the String at index[r][c]
	// with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	// that are not "vaccinated" will also be infected, and any adjacent to
	// them as well etc.
	// Infecting someone who is already infected has no effect
	// Trying to infect outside the confines of the grid also has no effect
	// Precondition: grid has no null entries
	public static void infect(String[][] grid, int r, int c) {
		if (r >= grid.length) {
			r = grid.length - 1;
		} else if (r < 0) {
			r = 0;
		}
		
		if (c >= grid[0].length) {
			c = grid[0].length - 1;
		} else if (c < 0) {
			c = 0;
		}

		if (grid[r][c].equals("infected") || grid[r][c].equals("vaccinated")) {
			return;
		} else {
			grid[r][c] = "infected";
		}

		infect(grid, r, c + 1);
		infect(grid, r, c - 1);
		infect(grid, r + 1, c);
		infect(grid, r - 1, c);		
	}

	// How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	// e.g. for n = 4, the subsets are {}, {1}, {2}, {3}, {4},
	// {1, 3}, {1, 4}, {2, 4}
	// The other subsets of 1,2,3,4 that DO contain consecutive integers are
	// {1,2}, {2,3}, {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {1,2,3,4}
	// Precondition: n > 0
	public static long countNonConsecutiveSubsets(int n) {
		if (n <= 2) {
			return n + 1; 
		} 
		
		long num = countNonConsecutiveSubsets(n - 1) + countNonConsecutiveSubsets(n - 2);

		return num;
	}

	// A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	// How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	// Precondition: n > 0
	public static long countWaysToJumpUpStairs(int n) {
		if (n == 0) {
			return 1;
		}

		if (n < 3) {
			return n;
		}

		long num = countWaysToJumpUpStairs(n - 1) + countWaysToJumpUpStairs(n - 2) + countWaysToJumpUpStairs(n - 3);
		return num;
	}

	// Everything above this line does NOT require a recursive helper method
	// ----------------------------------
	// Everything below this line requires a recursive helper method
	// Any recursive helper method you write MUST have a comment describing:
	// 1) what the helper method does/returns
	// 2) your description must include role of each parameter in the helper method

	// Prints all the subsets of str on separate lines
	// You may assume that str has no repeated characters
	// For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac",
	// "bc", "abc"
	// Order is your choice

	// prints subsets of str, with two recursive cases: 
	// either the character is included in the substring, or it isn't.
	// String str - string you are printing subsets of
	// int index - tracks the character the function is on
	// String current - where the subset is saved
	// returns void
	public static void findSubsets(String str, int index, String current) { 
		if (index == str.length()) {
			System.out.println(current);
			return;
		}

		// ex: a, b, c etc. -> b, c etc. -> c -> ""
		findSubsets(str, index + 1, current + str.charAt(index));
		// a, b, no c -> b, no c -> no c
		findSubsets(str, index + 1, current);
	}
	
	public static void printSubsets(String str) {
		if (str == null) {
			throw new IllegalArgumentException();
		}

		findSubsets(str, 0, "");
	}

	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice

	// prints permutations of a string by iterating over a pool of remaining characters, 
	// removing a character, and using recursion until remaining characters hits 0 for every such character
	// String str: the string holding the permutation
	// String remaining: the as-of-yet unused characters
	// returns void
	public static void findPermutations(String str, String remaining) {
		if (remaining.length() == 0) {
			System.out.println(str);
			return;
		}

		for (int i = 0; i < remaining.length(); i++) {
			char c = remaining.charAt(i);
			String nextRemaining = remaining.substring(0, remaining.indexOf(c)) 
				+ remaining.substring(remaining.indexOf(c) + 1);
			findPermutations(str + c, nextRemaining);
		}
	}

	public static void printPermutations(String str) {
		if (str == null) {
			throw new IllegalArgumentException();
		}

		findPermutations("", str);
	}

	// Performs a mergeSort on the given array of ints
	// Precondition: you may assume there are NO duplicates!!!
	public static void mergeSort(int[] ints) {
		if (ints.length == 2) {
			if (ints[0] > ints[1]) {
				int temp = ints[0];
				ints[0] = ints[1];
				ints[1] = temp;
			}

			return;
		} else if (ints.length <= 1) {
			return;
		}

		int[] lower = new int[ints.length / 2];
		int[] upper = new int[ints.length - lower.length];
		for (int i = 0; i < ints.length; i++) {
			if (i < ints.length / 2) {
				lower[i] = ints[i];
			} else {
				upper[i - ints.length / 2] = ints[i];
			}
		}

		mergeSort(lower);
		mergeSort(upper);

		int j1 = 0;
		int j2 = 0;
		for (int i = 0; i < ints.length; i++) {
			if (j1 >= lower.length && j2 >= upper.length) {
				return;
			} else if (j1 >= lower.length) {
				ints[i] = upper[j2];
				j2++;
			} else if (j2 >= upper.length) {
				ints[i] = lower[j1];
				j1++;
			} else {
				if (lower[j1] < upper[j2]) {
					ints[i] = lower[j1];
					j1++;
				} else {
					ints[i] = upper[j2];
					j2++;
				}
			}
		}
	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!

	// does quick sort on an ArrayList
	// ArrayList<Integer> intsList: the list to sort
	// returns sorted intsList
	public static void quickSortArrayLists(ArrayList<Integer> intsList) {
		if (intsList.size() <= 1) {
			return;
		}

		int pivot = intsList.get(intsList.size() / 2);
		ArrayList<Integer> lower = new ArrayList<>();
		ArrayList<Integer> upper = new ArrayList<>();		
		for (int i = 0; i < intsList.size(); i++) {
			if (intsList.get(i) < pivot) {
				lower.add(intsList.get(i));
			} else if (intsList.get(i) > pivot) {
				upper.add(intsList.get(i));
			}
		}

		quickSortArrayLists(lower);
		quickSortArrayLists(upper);

		for (int i = 0; i < intsList.size(); i++) {
			if (i < lower.size()) {
				intsList.set(i, lower.get(i));
			} else if (i == lower.size()) {
				intsList.set(i, pivot);
			} else {
				intsList.set(i, upper.get(i - (lower.size() + 1)));
			}
		}
	}

	public static void quickSort(int[] ints) {
		ArrayList<Integer> intsList = new ArrayList<Integer>();
		for (int i = 0; i < ints.length; i++) {
			intsList.add(ints[i]);
		}

		quickSortArrayLists(intsList);

		for (int i = 0; i < ints.length; i++) {
			ints[i] = intsList.get(i);
		}
	}

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 -> 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.

	// prints the steps to solve a hanoi tower
	// int startingDisks: number of disks in the tower
	// int start: where the disks start
	// int end: where you want the stack to go
	// int helper: the other pole
	// returns void
	public static void printHanoi(int startingDisks, int start, int end, int helper) {
		if (startingDisks == 0) {
			return;
		}
		
		printHanoi(startingDisks - 1, start, helper, end);
		System.out.println(start + " -> " + end);
		printHanoi(startingDisks - 1, helper, end, start);
	}

	public static void solveHanoi(int startingDisks) {		
		if (startingDisks < 0) {
			throw new IllegalArgumentException();
		}

		printHanoi(startingDisks, 0, 2, 1);
	}

	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places. You have an array, times[], that lists at which
	// MINUTE an item is available. Times is sorted in ascending order.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	// For example, if times = [3, 7, 9]
	// and points = [10, 15, 10]
	// Then the best possible result is getting the item at time 3 and the one at
	// time 9
	// for a total of 20 points, so it would return 20.

	// finds the max reward for the scavenger hunt
	// int[] times: the list of times
	// int[] points: the list of points at those times
	// int currIndex: the current index we are checking the optimality of
	// returns the maximum reward at a certain index
	public static int maxReward(int[] times, int[] points, int currIndex) {
		if (currIndex == times.length - 1) {
			return points[currIndex];
		}

		int nextIndex = currIndex;
		while (times[nextIndex] < 5 + times[currIndex]) {
			nextIndex++;

			if (nextIndex == times.length - 1) {
				break;
			}
		}

		if (times[nextIndex] < 5 + times[currIndex]) {
			return points[currIndex];
		}

		int with = points[currIndex] + maxReward(times, points, nextIndex);
		int without = maxReward(times, points, currIndex + 1);

		return (with > without ? with : without);
	}

	public static int scavHunt(int[] times, int[] points) {
		return maxReward(times, points, 0);
	}

}
