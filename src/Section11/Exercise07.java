package Section11;

import java.util.Random;
import java.util.Scanner;

public class Exercise07 {
	// useful: String word = "name@mail.com"
	// useful: char[] letters = word.toCharArray();     // changes the String into an array of chars
	public static void run() {
//		String[] wordList = {"magic", "wizard", "spell", "potion", "alchemy", "sorcery", "wand",
//								  "dragon", "cauldron", "broomstick"};
		String[] wordList = {"wizard"};
		String[] shuffledList = new String [wordList.length];
		int counter = 0; // counts the current index of the wordList
		
		Scanner scan = new Scanner(System.in);
		Random rnd = new Random();
		
		shuffleWords(wordList[0]);
	}
	
	// shuffles the letters of the passed String
	public static String shuffleWords(String word) {
		char[] letters = word.toCharArray();
		String shuffled = "";
		int[] indices = new int[word.length()];

		Random rnd = new Random();
		
		// fill the indices-array
		for (int fillUp = 0; fillUp < indices.length; fillUp++) {
			indices[fillUp] = fillUp;
		}
		
		// shuffle-algorithm (Fisher-Yates)
		for (int i = (word.length() - 1); i > 0; i-- ) {
			int randomizer = rnd.nextInt(i + 1);
			int temp = indices[i];
			indices[i] = indices[randomizer];
			indices[randomizer] = temp;
		}
		
		for (int j = 0; j < indices.length; j++) {
			shuffled += letters[indices[j]];
		}
		
		System.out.println("Klappt: " + shuffled);
		return shuffled;
	}
	
	// shuffles the indices of the passed String-array
	public static int[] sequence(String[] wordAmount) {
		int[] wordSequence = new int[wordAmount.length];
		return wordSequence;
	}
}



// Aktueller Stand: Ein Wort wird passend vermischt
// Wichtigste Frage: Warum funktioniert der Algorithmus so, mit dieser Zeile: 
// --> int randomizer = rnd.nextInt(i + 1);

// Gedankenstütze für morgen:
// 1. indices-Shuffle so umschreiben, dass man eine Methode nutzen kann um sowohl Worte als auch Arrays
//    zu shufflen
// 2. shuffleWords ggf. so umschreiben, dass direkt der komplette Array gemischt und zurückgegeben wird
// 3. die geshuffleten Indizes als Array zurückgeben, sodass in einem größeren Logikkörper der Shuffle-Array
//    mit den Original-Worten verglichen werden kann