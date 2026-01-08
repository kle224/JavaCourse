package Section10;

public class Exercise02 {
	public static void run() {
		// declaring a 2 dimensional array with 4 rows and 3 columns
		int[][] my2DArray = new int[4][3];
		
		// assign a value for each element of the array
		// going into the row →→→
		for (int i = 0; i < my2DArray.length; i++) {
			// going into each column of the row ↓↓↓
			for (int j = 0; j < my2DArray[i].length; j++) {
				my2DArray[i][j] = j + 1; 
			}
		}
		
		// print all elements of the array
		for (int i = 0; i < my2DArray.length; i++) {
			for (int j = 0; j < my2DArray[i].length; j++) {
				System.out.print(i + "" + my2DArray[i][j] + " "); 
			}
			System.out.println("");
		}
	}
}