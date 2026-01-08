package Section10;

public class Exercise03 {
	public static void run() {
		int[][] my2DArray = { {1,2,3},	 // first row
							  {4,5,6},	 // second row
							  {7,8,9} }; // third row
		
		// iterate through the array with an enhanced for loop
		// creating a 1D-Array out of each row of a 2D-Array
		for (int[] rows : my2DArray) {   
			// creating a variable for each element of a 1D-Array
			for (int element: rows) {    
				System.out.print(element + " ");
			}
			System.out.println("");
		}
	}
}