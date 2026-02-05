package Section12;

import java.util.Scanner;

public class Exercise03 {
	public static void run() {
		int choice = 0;
		String input = "zyxw";
		String output = "";
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to the Encoder-Decoder program!");
		System.out.println("1. Encode a string.");
		System.out.println("2. Decode a string.");
		System.out.print("Please choose an option (1 or 2): ");
		choice = scan.nextInt();
		scan.nextLine();
		
		
		if (choice == 1 || choice == 2) {
			System.out.print("\nPlease enter your string: ");
			input = scan.nextLine();
			
			if (choice == 1) {
				output = crypt(input, 0);
				System.out.printf("Your string \"%s\" was encoded into \"%s\". %n",
						input, output);
			} else if (choice == 2) {
				output = crypt(input, 0);
				System.out.printf("Your string \"%s\" was decoded into \"%s\". %n",
						input, output);
			} else {
				System.out.println("ERROR! You can only encode or decode strings!");
			}
		}
		
		scan.close();
	}
	
	public static char atbash(char ch) {
		if (ch >= 'a' && ch <= 'z') {
			return (char) ('z' - (ch - 'a'));
		} else if  (ch >= 'A' && ch <= 'Z') {
			return (char) ('Z' - (ch - 'A'));
		} else {
			return ch;
		}
	}
	
	// encodes or decodes a string input (letters a-z || A-Z)
	public static String crypt(String input, int index) {
		if (index == input.length()) {
			return "";		// base case
		}
		char ch = input.charAt(index);
		char cryptedCH = atbash(ch);
		return cryptedCH + crypt(input, index + 1);
	}
}