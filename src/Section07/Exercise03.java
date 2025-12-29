package Section07;

import java.util.Scanner;

public class Exercise03 {
	public static void run() {
		char grade;
		Scanner scan = new Scanner(System.in);
		
		// switch statements for the Char-datatype
		
		System.out.print("Please enter your grade: ");
		// takes a string input but stores only the 1st char and converts it into an uppercase letter.
		grade = scan.next().toUpperCase().charAt(0);
		
		switch (grade) {
		case 'A':
			System.out.println("Excellent! :)");
			break;
		case 'B':
			System.out.println("Very good! :)");
			break;
		case 'C':
			System.out.println("Good work!");
			break;
		case 'D':
			System.out.println("You passed!");
			break;
		case 'E':
			System.out.println("You could've done better!");
			break;
		case 'F':
			System.out.println("Maybe next time...");
			break;
		default:
			System.out.println("This grade doesn't exist...");
			break;
		}

		scan.close();
	}
}
