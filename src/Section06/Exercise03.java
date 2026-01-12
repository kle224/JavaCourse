package Section06;

public class Exercise03 {
	public static void run() {
		int a = 5, b = 10;
		
		// Example for nested if-else-statements
		
		if ( a > 0) {
			if (b > a) {
				System.out.println("B is greater than A!");
			} else {
				if (b == a) {
					System.out.println("B is equal to A!");
				} else {
					System.out.println("B is lesser than A!");
				}
			}
		}
	}
}