package Section08;

import java.util.Scanner;

public class Exercise02 {
	public static void run() {
		int levels;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter the number of levels you want to see printed: ");
		levels = scan.nextInt();
		for (int i = 1; i <= levels; i++) {
//			System.out.printf("Iteration: %02d %n", i);
			System.out.print(i + ".: ");
			for (int spaces = 0; spaces < (levels - i); spaces++) {
				System.out.print("-");
			}
			// Hier hängt es gerade
			// 
			for (int stars = i; stars <= (levels + (levels - 1)); stars++) {
				System.out.print("*");
			}
			System.out.println("ß");
		}
		scan.close();
	}
}