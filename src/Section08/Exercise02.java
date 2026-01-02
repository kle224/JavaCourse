package Section08;

import java.util.Scanner;

public class Exercise02 {
	public static void run() {
		int levels;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter the number of levels you want to see printed: ");
		levels = scan.nextInt();
		for (int i = 1; i <= levels; i++) {
			
			// print spaces
			for (int spaces = 0; spaces < (levels - i); spaces++) {
				System.out.print(" ");
			}
			
			// print stars
			for (int stars = 0; stars < (2 * i) - 1; stars++) {
				System.out.print("*");
			}
			System.out.println();
		}
		scan.close();
	}
}