package Section06;

import java.time.chrono.IsoChronology;

public class Exercise06 {
	public static void run() {
		int number = -5;
		
		// example for nested if-else-control-statements
		
		if (number > 0) {
			System.out.println("Number is positive.");
		} else {
			if (number < 0) {
				System.out.println("Number is negative.");
			} else {
				System.out.println("Number is zero.");
			}
		}
	}
}
