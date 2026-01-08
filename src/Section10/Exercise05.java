package Section10;

public class Exercise05 {
	public static void run() {
		int[][] my2DArray = { {1,2,3},	 // first row
							  {4,5,6},	 // second row
							  {7,8,9} }; // third row
		int sum = 0;
		
		// try to get the sum of all elements of the array (should be 45)
		// btw: mathematical formular for this is "(n/2) * (1st + last Number)
		// n = number of numbers
		// makes this one to: (9/2) * (1+9) = 4,5 * 10 = 45
		
		for (int[] rows : my2DArray) {
			for (int element : rows) {
				sum += element;
			}
		}
		System.out.printf("The sum of all elements of the array is: %d. %n", sum);
	}
}