package Section06;

import java.util.Scanner;

public class Exercise08 {
	public static void run() {
		int age;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please enter your age: ");
		age = scan.nextInt();
		double discount = ((age < 18) ? 0.10 : 0.00) * 100; // everyone who is younger than 18 will get a 10% discount
		
		System.out.printf("Your discount is %.2f%%. %n", discount);
		
		System.out.print("Please enter the score of your test: ");
		int score = scan.nextInt();
		char grade = (score >= 90) ? 'A'
				   : (score >= 85) ? 'B'
				   : (score >= 65) ? 'C'
				   : (score >= 50) ? 'D'
				   : (score >= 30) ? 'E'
				   : 'F';
		System.out.printf("Your grade is %c.%n", grade);
	}
}
