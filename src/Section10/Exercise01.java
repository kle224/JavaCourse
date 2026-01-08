package Section10;

public class Exercise01 {
	public static void run() {
		// declaring a 3x3 array
	//  int[2][D]name = new int[row][column];
		int[][] nums1 = new int[ 3 ][  3   ];
		
		// declaring and initializing a 3x3 array with it's values
		int[][] nums2 = { {1,2,3}, {4,5,6}, {7,8,9}, {1,2,3,4} };
		
		// iterating through 2D-arrays with nested loops
		
		// using for-loops:
		outerLoop:									// outer loop for rows:     (→Zeilen→)
		for (int i = 0; i < nums2.length; i++) {
			innerLoop:								// inner loop for columns: (↓Spalten↓)
			for (int j = 0; j < nums2[i].length; j++) {
				System.out.print(nums2[i][j] + " ");
			}
			System.out.println("");
		}
		
		// using foreach/enhanced for-loops:
		System.out.println("\n" + "foreach:");
		for (int[] row : nums2) {
			for (int element : row) {
				System.out.print(element + " ");
			}
			System.out.println("");
		}
		
		// Accessing a 2D-Array
		System.out.println("\n---");
		int element = nums2[2][1]; // row 3 --- column 2 => output = 8
		System.out.println("Accessing element: " + element + "\n---\n");
		
		// Modify Elements of a 2D-Array
		System.out.println("\n---");
		nums2[2][1] = 15; // replacing row 3 --- column 2 => it's now 15 instead of 8
		System.out.println("Modified element (was " + element + "): " + nums2[2][1] 
						  + "\n---\n");
	}
}