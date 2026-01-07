package Section09;

import java.util.Arrays;
import java.util.Collections;

public class Exercise10 {
	public static void run() {
		int nums[] = new int[5];
		
		// fills the entire nums-array with 9s
		Arrays.fill(nums, 9);
		
		// fills the nums-array elements 0-2 with 10s 
		Arrays.fill(nums, 0,3,10);
		// fills the nums-array elements 3-4 with 11s
		Arrays.fill(nums, 3,5,11);
		
		System.out.println("nums: " + Arrays.toString(nums));
		
		// declaring and initializing a new array with the same values
		int[] myNums = {10,10,10,11,11};
		// comparing the arrays to get a boolean value that indicates their equality
		System.out.println("Equals: " + Arrays.equals(nums, myNums));
	}
}