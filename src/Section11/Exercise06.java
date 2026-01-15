package Section11;

import java.util.Random;

public class Exercise06 {
	// creating a random number
	public static void run() {
		Random rnd = new Random();
		int counter0 = 0, counter1 = 0;
		int avg0 = 0, avg1 = 0;
		int randomNo;
		
		// do 5 rounds
		for (int i = 1; i <= 5; i++) {
			// create 50 numbers per round and count if it's a 0 or a 1
			for (int j = 0; j < 50; j++) {
				// create a random number between (included:) 0 and (everything lower than) 2 
				randomNo = rnd.nextInt(0,2); // if empty: creates a completely random integer
				
				if (randomNo == 0)
					counter0++;
				else
					counter1++;
			}
			avg0 += counter0;
			avg1 += counter1;
			
			System.out.printf("%02d: 0-counter: %d  |  1-counter: %d %n", i, counter0, counter1);
			counter0 = 0;
			counter1 = 0;
		}
		
		System.out.printf("   Average 0s: %d  | Average 1s: %d %n", avg0, avg1);
	}
}