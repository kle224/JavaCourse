package Section11;

import java.util.Scanner;

public class Exercise05 {
	public static void run() {
		int no0, no1;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please enter your 1st number: ");
		no0 = scan.nextInt();
		
		System.out.print("Please enter your 2nd number: ");
		no1 = scan.nextInt();
		
		// assign the return value of the add-Method to a variable
		int result = add(no0, no1);
		System.out.println("The result of your numbers (stored in a variable) is: " + result);
		
		// alternative print the returning int via calling the method in the print statement
		System.out.println("The sum of your numbers (called in the statement) is: " + add(no0, no1));
		
		/////////////////////////////////////////////// substract
		System.out.println("The difference of your numbers (called in the statement) is: " + substract(no0, no1));
		scan.close();
	}
	
	// using the keyword "int" instead of "void" suggests, that the Method will return a value of the type int
	public static int add(int no0, int no1) {
		return no0 + no1;
	}
	
	public static int substract(int no0, int no1) {
		return no0 - no1;
	}
}