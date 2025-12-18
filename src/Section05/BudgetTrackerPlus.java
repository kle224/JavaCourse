package Section05;

import java.util.Scanner;

public class BudgetTrackerPlus {
	public void run() {
		Scanner scan = new Scanner(System.in);
		
		double income; // income
		double rent, groceries, transportation, entertainment; // expenditures
		double totalExpense, remaining, percent; // results
		
		// asking for inputs
		System.out.print("Hello! :) \nPlease enter your income: ");
		income = scan.nextDouble();
		System.out.println(""); // breaks the line
		
		System.out.println("Thank you! \n\nPlease enter now the requested values:");
		
		System.out.print("Rent: ");
		rent = scan.nextDouble();
		
		System.out.print("Groceries: ");
		groceries = scan.nextDouble();
		
		System.out.print("Transportation: ");
		transportation = scan.nextDouble();
		
		System.out.print("Entertainment: ");
		entertainment = scan.nextDouble();
		System.out.println(""); // breaks the line
		
		// calculations
		totalExpense = rent + groceries + transportation + entertainment;
		remaining = income - totalExpense;
		percent = (totalExpense / income) * 100; 
		
		// output
		System.out.println(String.format("Your totals expanses are: $%.2f", totalExpense));
		
		if (remaining > 0) {
			System.out.println(String.format("You have $%.2f left.", remaining));
		} else if (remaining == 0) {
			System.out.println("You don't have any money left after your expenditures!");
		} else {
			System.out.println(String.format("After all of your expenditures you loose $%.2f", (-remaining)));
		}
		
		System.out.println(String.format("You spend %.2f%% of your income.", percent));
		
		scan.close();
	}
}