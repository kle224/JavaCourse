package Section08;

public class Exercise01 {
	public static void run() {
		
		// Introduction to loops with simple examples
		
		// for-loop
		// for (initialization; condition; iteration) {}
		for (int counter = 0; counter < 5; counter++) {
			int iterations = counter + 1;
			System.out.printf("For-Loop: iteration = %02d --- counter = %02d. %n", iterations, counter);
		}
	}
}