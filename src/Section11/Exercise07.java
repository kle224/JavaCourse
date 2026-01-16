// useful: String word = "name@mail.com"
// useful: char[] letters = word.toCharArray();     // changes the String into an array of chars

package Section11;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Exercise07 {
	public static void run() {
		String[] wordList = {"magic", "wizard", "spell", "potion", "alchemy", "sorcery", "wand",
								  "dragon", "cauldron", "broomstick"};
		String[] shuffledListCopy = Arrays.copyOf(wordList, wordList.length);
		String[] shuffledList = new String [wordList.length];
		int counter = 0; // counts the current index of the wordList
		int trys = 2;	 // counter for invalid/wrong inputs
		boolean compared;
		boolean playing = true;
		
		Scanner scan = new Scanner(System.in);
		Random rnd = new Random();
		
		// shuffling the array before the game starts and store it into a backup for later comparison
		shuffledListCopy = shuffle(shuffledListCopy, rnd);
		
		// shuffling each element in the string array and place it in a shuffled array
		for (int i = 0; i < shuffledList.length; i++) {
			shuffledList[i] = shuffle(shuffledListCopy[i], rnd);
		}
		
		while (playing) {
			if (counter == 0)
				System.out.println("Greetings fellow scrabler and welcome to our little game! :)");
			
			System.out.printf("Can you solve the current word \"%s\"? %n", shuffledList[counter]);
			System.out.print("Please enter your guess: ");
			compared = compare(shuffledListCopy[counter], scan.nextLine());
			
			if (compared) {
				System.out.printf("Congratulations! You solved the word, it was indeed %s. %n",
						shuffledListCopy[counter]);
				counter++;
			} else if (!compared && trys > 0) {
				System.out.printf("You missed it... Want to guess again? The word was \"%s\"%n",
						shuffledList[counter]);
				trys--;
			} else {
				System.out.println("All of your attempts failed... good luck for the next time :)");
				break;
			}
			
			if (counter == shuffledList.length) {
				System.out.println("You unscrabled all words!");
				break; // game won
			}
			
			if (compared) {
				System.out.println("Do you want to unscrable another word?");
				System.out.print("Enter any key to continue or \"x\" to quit: ");
				boolean quitter = quit("X", scan.nextLine());
				
				if (quitter) {
					System.out.println("Goodbye!");
					playing = false;
				} else {
					trys = 2;
				}
			}
		}
		
		System.out.println("Your game is finished!");
		System.out.printf("You reached %d points in total. %n", counter);
		
		scan.close();
	}
	
	// shuffles a passed String
	public static String shuffle(String word, Random rnd) {
		char[] letters = word.toCharArray();
		
		for (int i = (letters.length - 1); i > 0; i--) {
			int j = rnd.nextInt(0, i+1);
			char temp = letters[i];
			letters[i] = letters[j];
			letters[j] = temp;
		}

		String shuffledWord = new String(letters);
		
		return shuffledWord;
	}
	
	// shuffles a passed String-Array
	public static String[] shuffle(String[] array, Random rnd) {
		
		for (int i = (array.length - 1); i > 0; i--) {
			int j = rnd.nextInt(0, i+1);
			String temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		
		return array;
	}
	
	// compares strings
	public static boolean compare(String original, String compareTo) {
		return original.equalsIgnoreCase(compareTo);
	}
	
	// calling the compare method to create a quit statement
	public static boolean quit(String quitStatement, String compareTo) {
		boolean quitter = compare(quitStatement, compareTo);
		return quitter;
	}
}