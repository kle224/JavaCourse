package Section06;

import java.security.DrbgParameters.NextBytes;
import java.util.Scanner;

public class Exercise02 {
	public static void run() {
		int test;
		String name;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please enter a whole number: ");
		test = scan.nextInt();
		scan.nextLine();
		
		if (test > 0) {
			System.out.println("Inside 1st test (if).");
			System.out.printf("%d is a positive number!%n", test);
		} else if (test < 0) {
			System.out.println("Inside 1st test (else if).");
			System.out.printf("%d is a negative number!%n", test);
		} else {
			System.out.println("Inside 1st test (else).");
			System.out.printf("%d is zero! %n", test);
		}
		
		System.out.println("Please enter your name: ");
		name = scan.nextLine();
		System.out.println("name: " + name);
		
	}
}