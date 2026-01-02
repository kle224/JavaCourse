package Section08;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;

public class Exercise02 {
	public static void run() {
		int levels;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter the number of levels you want to see printed: ");
		levels = scan.nextInt();
		for (int i = 1; i <= levels; i++) {
			
			// print spaces
//			Beispiel nach Reihen:
//			1. Condition wird vier Mal erfüllt
//			2. Condition wird drei Mal erfüllt
//			3. Condition wird zwei Mal erfüllt
//			4. Condition wird ein Mal erfüllt
//			5. Condition wird null Mal erfüllt
			for (int spaces = 0; spaces < (levels - i); spaces++) {
				System.out.print(" ");
			}
			
			// print stars
//			Beispiel nach Reihen:
//				1. Condition wird ein Mal erfüllt
//				2. Condition wird drei Mal erfüllt
//				3. Condition wird fünf Mal erfüllt
//				4. Condition wird sieben Mal erfüllt
//				5. Condition wird neun Mal erfüllt
			for (int stars = 0; stars < (2 * i) - 1; stars++) {
				System.out.print("*");
			}
			System.out.println();
		}
		scan.close();
	}
}