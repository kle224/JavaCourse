package Section11;

import java.util.Scanner;

public class Exercise03 {
	public static void run() {
		int cheeseAmount = 0;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please let us know how much vegan cheese you want to have on your sandwich: ");
		cheeseAmount = scan.nextInt();
		scan.nextLine();
		
		// passing the int variable "cheeseAmount" as parameter into the "makeSandwich"-Method
		makeSandwich(cheeseAmount);
		
		System.out.println("");
		
		System.out.print("Please let us know how much vegan cheese you want on your sandwich: ");
		// passing a direct input as parameter into the "makeSandwich"-Method
		makeSandwich(scan.nextInt());
		
		scan.close();
	}
	
	public static void makeSandwich(int cheesePieces) {
		System.out.println("Take two slices of bread.");
		System.out.println("Spread butter on one slice.");
		
		for (int i = 0; i < cheesePieces; i++) {
			if (i == 0)
				System.out.println("Add a slice of vegan cheese.");
			else
				System.out.println("Add another slice of vegan cheese.");
		}
		
		System.out.println("Spread butter on the second slice.");
		System.out.println("Put the second slice of bread on top.");
		System.out.println("Your sandwich is ready!");
	}
}