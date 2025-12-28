package Section06;

import java.lang.classfile.constantpool.DoubleEntry;
import java.util.Scanner;

public class Exercise01 {
	public void run() {
		System.out.println("Example for the ternary statement:");
		
		int age;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("The discount depends on your age. Children below 13 years will pay less.");
		
		System.out.println("Enter your age: ");
		age = scan.nextInt();

		// Ternary guide:
		// type name = condition ? if true : if false;
		double discount = (age <= 12) ? 0.10 : 0.05;
		System.out.printf("age: %d years.%n", age);
		
		
		System.out.printf("Discount for age %d is %.2f%%. %n", age, (discount * 100));
	}
}
