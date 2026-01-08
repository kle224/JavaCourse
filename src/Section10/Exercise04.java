package Section10;

public class Exercise04 {
	public static void run() {
		int[][] my2DArray = { {1,2,3},	 // first row
							  {4,5,6},	 // second row
							  {7,8,9} }; // third row
		int largest = my2DArray[0][0];
		int lowest = my2DArray[2][2];
		
		// get the largest and lowest number out of a 2D-Array
		for (int[] rows : my2DArray) {
			for (int element : rows) {
				if (element > largest) {
					largest = element;
				}
				
				if (element < lowest) {
					lowest = element;
				}
			}
		}
		System.out.printf("The largest element is: %d. %n", largest);
		System.out.printf("The lowest element is: %d. %n", lowest);
	}
}