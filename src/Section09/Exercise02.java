package Section09;

public class Exercise02 {
	public static void run() {
		// declaring an array
		// this array just has allocated space, but there are no values defined for it yet
		int[] numbers1 = new int[5];
		
		// declaring and initialize an array
		int[] numbers2 = {1,2,3,4,5};
		
		// using the first element of the array
		int firstNum = numbers2[0];
		
		System.out.printf("First element of the \"numbers2\"-array"
				+ " rigth after initialization: %d. %n", firstNum);
		
		// reassigning the array element and the variable
		numbers2[0] = 123;
		firstNum = numbers2[0];
		
		System.out.printf("First element of the \"numbers2\"-array"
				+ "right after the reassignment: %d. %n", firstNum);
	}
}