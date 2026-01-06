package Section09;

import java.util.Arrays;

public class Exercise08 {
	public static void run() {
		// declare and initialize an int array and a variable for a searched value
		int[] nums = {2,5,1,3,4,7,3,8};
		int searched = 5;
		
		Arrays.sort(nums);
		int index = Arrays.binarySearch(nums, searched);
		System.out.printf("Match for number \"%d\" found @ index %02d in this array \"%s\". %n", 
				searched, index, Arrays.toString(nums));
	}
}