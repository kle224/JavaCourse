package Section08;

import java.util.Scanner;

public class Exercise08 {
	public static void run() {
		Scanner scan = new Scanner(System.in);
		
		// using the "continue" keyword in loops
		// jumps to the closing curly brace of the loop
		// if it's a for-loop, the loops iteration will be triggered
		// if the loops condition is still true, the loop will be executed again
		
		for (int i = 1; i <= 15; i++) {
			if (i == 7) {
				System.out.println("Ignoring this one");
				continue;
			}
			System.out.printf("Current: %02d.%n", i);
		}
	}
}