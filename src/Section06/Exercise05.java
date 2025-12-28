package Section06;

import java.util.Scanner;

public class Exercise05 {
	public static void run() {
		int number;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please enter a whole number: ");
		number = scan.nextInt();
		
		if (number % 2 == 0) {
			System.out.printf("The number %d is an even number. %n", number);
		} else {
			System.out.printf("The number %d is an odd number. %n", number);
		}
	}
}
