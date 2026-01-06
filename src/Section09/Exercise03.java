package Section09;

public class Exercise03 {
	public static void run() {
		int[] numbers = {49,58,67,76,85,94};
		
		// looping through arrays with a for loop
		
		for (int i = 0; i < numbers.length; i++) {
			System.out.printf("Iteration: %02d. --- element value: %d. %n",(i+1), numbers[i]);
		}
	}
}