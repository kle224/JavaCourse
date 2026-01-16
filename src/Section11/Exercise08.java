package Section11;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Exercise08 {
	public static void run() {
		Scanner scan = new Scanner(System.in);
		Random rnd = new Random();
		
		System.out.print("Please enter your email: ");
		isValidMail(scan.nextLine());
	}
	
	public static boolean isValidMail(String mail) {
		char[] elements = mail.toCharArray();
		
		searchCharacter(elements, '@');
		
		return true;
	}
	
	public static void searchCharacter(char[] characters, char searched) {
		int searchedCounter = 0;
		
		for (char part : characters) {
			if (part == searched) {
				searchedCounter++;
			}
		}
		System.out.println("Looking for @: " + Arrays.binarySearch(characters, searched));
	}
}