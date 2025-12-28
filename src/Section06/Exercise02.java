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
		
		System.out.print("Please enter your name: ");
		name = scan.nextLine();
		
		if (name.equalsIgnoreCase("Noah")) {
			System.out.println("Inside 2nd test (if).");
			System.out.printf("The name \"%s\" is the same as yours.%n", name);
		} else {
			System.out.println("Inside 2nd test (else).");
			System.out.printf("The name \"%s\" is different from yours.%n", name);
		}
		
	}
}