package Section10;

public class Exercise01 {
	public static void run() {
		// declaring a 3x3 array
		int[][] nums1 = new int[3][3];
		
		// declaring and initializing a 3x3 array with it's values
		int[][] nums2 = { {1,2,3}, {4,5,6}, {7,8,9}, {1,2,3,4} };
		
		for (int i = 0; i < nums2.length; i++) {
			for (int j = 0; j < nums2[i].length; j++) {
				System.out.print(nums2[i][j] + " ");
			}
			System.out.println("");
		}
	}
}