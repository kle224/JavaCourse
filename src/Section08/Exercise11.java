package Section08;

import java.util.Scanner;

public class Exercise11 {
	public static void run() {
		// harshad numbers
		int x = 123, storage, sum = 0;
		boolean condition = true;
		String choice;
		Scanner scan = new Scanner(System.in);		
		
		while (condition) {
			System.out.print("Please enter your number: ");
			x = scan.nextInt();
			scan.nextLine();
			storage = x;
			
			while (storage > 0) {
				sum += storage % 10;	// cutting the last digit
				storage /= 10;			// Cutting all before the last digit
			}
			
			if ((x % sum) == 0) {
				System.out.printf("%d is divisible by its digits sum, %d, it is a Harshard number. %n", x, sum);
			} else {
				System.out.printf("%d isn't divisible by its digits sum, %d, it's not a Harshard number. %n", x, sum);
			}
			
			System.out.print("Do you want to enter a new number (yes or no)?: ");
			choice = scan.nextLine();
			
			if (choice.equalsIgnoreCase("yes")) {
				System.out.println("Perfect, please follow the program :)\n");
				sum = 0;
			} else {
				System.out.println("See you next time... ;)");
				condition = false;
			}
		}
		scan.close();
	}
}
