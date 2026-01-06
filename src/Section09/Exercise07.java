package Section09;

import java.util.Arrays;

public class Exercise07 {
	public static void run() {
		// declare and initialize an int and a string array
		int[] nums = {2,5,1,3,4,7,3,8};
		String[] names = {"Nico", "Oliver", "Jonas"};
		
		// print them with the Arrays.toString() Method
		System.out.println("Presorted nums:   " + Arrays.toString(nums));
		System.out.println("Presorted names:  " + Arrays.toString(names));
		
		// sort them in an ascending order with the Arrays.sort() Method
		Arrays.sort(nums);
		Arrays.sort(names);
		
		// print the sorted arrays with the Arrays.toString() Method
		System.out.println("Postsorted nums:  " + Arrays.toString(nums));
		System.out.println("Postsorted names: " + Arrays.toString(names));
	}
}