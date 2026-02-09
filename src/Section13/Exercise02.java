package Section13;

public class Exercise02 {
	public static void run() {
		int number = 1234512346;
		
		int counter = numberOfDigits(number, 0, 0);
		System.out.println(counter);
	}
	
	public static int numberOfDigits(int storage, int sum, int counter) {
		if (storage == 0) {
			return counter;
		}
		
		return numberOfDigits(storage /= 10, sum += storage % 10, ++counter);
	}
}