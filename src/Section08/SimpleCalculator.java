package Section08;

import java.util.Scanner;

public class SimpleCalculator {
	public static void run() {
		int no0, no1, result = 0;
		char operator;
		boolean condition = true;
		String choice;
		Scanner scan = new Scanner(System.in);
		
		while (condition) {
			System.out.print("Please enter the first number: ");
			no0 = scan.nextInt();
			scan.nextLine();
			
			System.out.print("Please enter the second number: ");
			no1 = scan.nextInt();
			scan.nextLine();
			
			System.out.print("Please enter the operator for your term: ");
			operator = scan.next().charAt(0);
			scan.nextLine();
			
			switch (operator) {
            case '+':
                result = no0 + no1;
                break;
            case '-':
                result = no0 - no1;
                break;
            case '*':
                result = no0 * no1;
                break;
            case '/':
                if (no0 != 0 && no1 != 0)
                    result = no0 / no1;
                else
                    System.out.println("Division by 0 is impossible.");
                break;
            default:
            	System.out.println("There is an error with your operator...");
                break;
			}
			System.out.printf("The term \"%d %s %d\" results in %d. %n", no0, operator, no1, result);
			
			System.out.print("Do you want to calculate another term? (enter yes or no): ");
            choice = scan.nextLine();
            
            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("Fine, please follow the programs guidance.");
                result = 0;
                continue;
            } else {
            	System.out.println("Calculator session ended. Goodbye!");
                condition = false;
            }
		}
	}
}