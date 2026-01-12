package Section09;

public class Exercise04 {
	public static void run() {
		int[] numbers = {49,58,67,76,85,94};
		int iteration = 1;

		// looping through arrays with a foreach loop
		// foreach in java is called enhanced for loop;
		
		for (int no : numbers) {
			// the logic is:
			// every element of the numbers-array is an int.
			// no will be in this case the alias for the array element in the
			// current iteration
			// e.g.: in the 1st iteration "no" will be like "numbers[0]"
			System.out.printf("Iteration: %02d. --- array element: %d.%n", iteration++, no);
		}
		iteration = 1;
		
		System.out.println("");
		
		String[] friends = {"Nico", "Oliver", "Jonas", "Jan", "Constantin"};
		
		for (String friend : friends) {
			System.out.printf("Please come to my party, %s. %n", friend);
		}
	}
}