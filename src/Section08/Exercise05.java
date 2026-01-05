package Section08;

import java.util.Scanner;

public class Exercise05 {
	public static void run () {
		// while loop
		Scanner scan = new Scanner(System.in);
		boolean condition = true;
		int number, counter;
		
		while (condition) {
			System.out.print("Please enter a whole number or 0 to exit: ");
			number = scan.nextInt();
			
			if (number == 0) {
				condition = false;
				System.out.println("Goodbye!");
			}
		}
	}
}
