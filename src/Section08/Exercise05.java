package Section08;

import java.util.Scanner;

public class Exercise05 {
	public static void run () {
		// while loop
		Scanner scan = new Scanner(System.in);
		boolean condition = true;
		int number, counter;
		
		// enter the while-loop if the condition is met
		// executing the code inside the loop
		// there has to be code that makes it possible to exit the loop
		// otherwise you'll create an infinite loop
		// exits the loop if the condition is no longer met
		
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
