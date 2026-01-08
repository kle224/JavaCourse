package Section10;

public class Exercise06 {
	public static void run() {
		int[][] mat = { {1,2,3},	 // first row
					    {4,5,6},	 // second row
					    {7,8,9} };   // third row
		int sumP = 0, sumS = 0;
		
		// Goal 1: sum of the primary diagonal (1,5,9)
		// Goal 2: sum of the secondary diagonal (3,5,7)
		
		for (int row = 0; row < mat.length; row++) {
			for (int no = 0; no < mat[row].length; no++) {
				if (row == no) {
					sumP += mat[row][no];
				}
			}
		}
		
		for (int rowB = mat.length - 1; rowB >= 0; rowB--) {
			for (int noB = mat[rowB].length - 1; noB >= 0; noB--) {
				if (rowB == noB) {
					sumS += mat[rowB][noB];
				}
			}
		}
		System.out.printf("Primary sum is: %d. %n", sumP);
		System.out.printf("Secondary sum is: %d. %n", sumS);
	}
}