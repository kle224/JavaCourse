package Section08;

import java.util.Scanner;

public class Exercise06 {
	public static void run() {
		Scanner scan = new Scanner(System.in);
		boolean condition;
		int number;
		
		// enter the do-while-loop always. even if the condition is never met!
		// executing the code inside the loop
		// there has to be code that makes it possible to exit the loop
		// otherwise you'll create an infinite loop
		// exits the loop if the condition is no longer met
		
		do {
			System.out.print("Please enter a whole number or 0 to end the loop: ");
			number = scan.nextInt();
			
			if (number > 0 || number < 0) {
				condition = false;
			} else {
				System.out.println("Bye, have great day!");
				condition = true;
			}
		} while (!condition);
	}
}