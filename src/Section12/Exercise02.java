package Section12;

import java.util.Scanner;

public class Exercise02 {
	public static void run() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please Enter your whole number: ");
		int number = scan.nextInt();
		
		int result = factorial(number, number);
		System.out.print(" = " + result);
		
		scan.close();
	}
	
	// what is a factorial?
	// → the factor of all whole numbers multiplied in row starting from "n" up to 1
	// e.g.: n = 4 → 4! = 4 * 3 * 2 * 1 = 24
	public static int factorial(int n, int original) {
		if (n <= 0) {
			return 1; // base case: gets us out of the recursion
		}
		
		if (n == original) {
			System.out.print(n + "! = " + n);
		} else {
			System.out.print(" * " + n);
		}
		// recursive case: repeats the operation with the same method using updated values
		return (n*factorial(n-1, original));
	}
}