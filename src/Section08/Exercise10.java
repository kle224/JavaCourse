package Section08;

import java.util.Scanner;

public class Exercise10 {
	public static void run() {
		String input, choice;
		int counterV = 0, counterC = 0;
		boolean condition = true;
		Scanner scan = new Scanner(System.in);
		
		things:
		while (condition) {
			System.out.print("Please enter your string (enter 'exit' to end the code!): ");
			input = scan.nextLine();
			
			if (input.equalsIgnoreCase("exit")) {
				System.out.println("Goodbye! :)");
				break things;
			}
			
			counting:
			for (int i = 0; i < input.length(); i++) {
				// checking if the current char of the string is a character of the alphabet
				if (Character.isLetter(input.charAt(i))) {
					// incrementing the counters dependent on vowel or consonant
					switch (input.charAt(i)) {
					case 'a', 'e', 'i', 'o', 'u':
						counterV++;
						break;
					default:
						counterC++;
						break;
					}
				} else {
					// skip this character because it's not in the alphabet
					continue counting;
				}
			}
			System.out.println("\nFrom your string \"" + input + "\" the counters are:");
			System.out.printf("vowels: %02d || consonants: %02d %n", counterV, counterC);
			
			System.out.print("Do you want to continue (yes or no)? ");
			choice = scan.nextLine();
			
			if (choice.equalsIgnoreCase("yes")) {
				System.out.println("Ok, here we go :)");
				// reset the counters if we're doing another check
				counterV = 0;
				counterC = 0;
			} else {
				System.out.println("See you next time!");
				condition = false;
			}
		}
		scan.close();
	}
}