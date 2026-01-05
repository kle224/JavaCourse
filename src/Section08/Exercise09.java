package Section08;

public class Exercise09 {
	public static void run() {
		int counter = 1;
		
		// labels, breaks and continues for loops
		// labeling a loop is like giving it a name
		// using keywords like break or continue in nested loops
		// can help you to control all of the code
		
		outerLoop:
		for (int i = 1; i <= 3; i++) {
			innerLoop:
			for (int j = 1; j <= 3; j++) {
				if (i == 2 && j ==2) {
					System.out.println("Breaked here!");
					continue innerLoop; // there won't be an output for "i: 2 | j: 2",
										// but "i: 2 | j: 3" will be there
				}
				System.out.printf("i: %d | j: %d %n", i, j);
			}
		}
		System.out.println("Loop done!");
	}
}