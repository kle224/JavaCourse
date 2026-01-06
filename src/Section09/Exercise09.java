package Section09;

import java.util.Arrays;

public class Exercise09 {
	public static void run() {
		int[] nums = {2,5,1,3,4,7,3,8};
		int[] notReallyABackupOfNums = nums; // this is only a reference
		int[] backupOfNumbs = Arrays.copyOf(nums, nums.length); // really a backup
		int[] lessNums = Arrays.copyOf(nums, 5); // if the newLength is greater than the last array.length, the method adds 0s.
		
		System.out.println("Presorted:    " + Arrays.toString(nums));
		
		Arrays.sort(nums);
		
		System.out.println("Postsorted:   " + Arrays.toString(nums));
		System.out.println("Reference:    " + Arrays.toString(notReallyABackupOfNums));
		System.out.println("Backup:       " + Arrays.toString(backupOfNumbs));
		System.out.println("Small Backup: " + Arrays.toString(lessNums));
	}
}