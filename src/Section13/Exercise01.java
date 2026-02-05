package Section13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Exercise01 {
	public static void run() {
		int[] original = new int[10];
		ArrayList<Integer> unique = new ArrayList<Integer>();
		
		Random rnd = new Random();
		
		for (int i = 0; i < original.length; i++) {
			original[i] = rnd.nextInt(0,10); // creates a random number from 0 up to 9 in the current index
		}

		int[] numbers = Arrays.copyOf(original, original.length);
		Arrays.sort(numbers);
		
		for (int i = 0; i < numbers.length; i++) {
			if (i == 0 && numbers[i] != numbers[i + 1]) {
				unique.add(numbers[i]);
			} else if (i == (numbers.length - 1) && numbers[i] != numbers[i - 1]) {
				unique.add(numbers[i]);
			}
			
			if (i < (numbers.length - 1) && i > 0 ) {
				if (numbers[i] != numbers[i + 1] && numbers[i] != numbers[i - 1]) {
					unique.add(numbers[i]);
				}
			}
		}
		
		System.out.println("All numbers: " + Arrays.toString(original));
		System.out.println("Unique numbers: " + unique);
	}
}