package Section06;

public class Exercise02 {
	public static void run() {
		int test = 5;
		
		if (test > 0) {
			System.out.println("Inside conditions (if).");
			System.out.printf("%d is a positive number!%n", test);
		} else if (test < 0) {
			System.out.println("Inside conditions (else if).");
			System.out.printf("%d is a negative number!%n", test);
		} else {
			System.out.println("Inside conditions (else).");
			System.out.printf("%d is null! %n", test);
		}
		
		System.out.println("Outside of conditions");
	}
}