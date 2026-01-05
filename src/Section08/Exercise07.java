package Section08;

import java.util.Scanner;

public class Exercise07 {
	public static void run() {
		Scanner scan = new Scanner(System.in);
		
		// using "break;" in loops
		
		for (int i = 1; i <= 15; i++) {
			if (i == 7) {
				System.out.println("Found our number!");
				break;
			}
			System.out.printf("Current: %02d.%n", i);
		}
	}
}
