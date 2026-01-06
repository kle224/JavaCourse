package Section09;

public class Exercise06 {
	public static void run() {
		// initializing the array
		int[] nums = {2,5,1,3,4,7};
		// search the breakpoint for each half of the array
		int n = nums.length / 2;
		// declaring a new array with the length of the first one
		int[] rearranged = new int[nums.length];
		
		System.out.print("Original array: ");
		for (int no : nums) {
			System.out.printf("%d ", no);
		}
		System.out.println("\n");
		
		// creating a loop that passes each element in first half of the array
		for (int i = 0; i < n; i++) {
			rearranged[2*i] = nums[i];
			rearranged[2*i+1] = nums[n+i];
			System.out.printf("Iteration %02d: Slot %d = %d and Slot %d = %d. %n", 
					i, (2*i), nums[i], (2*i+1), nums[n+i]);
		}
		
		System.out.print("\nOriginal:   ");
		for (int no : nums) {
			System.out.printf("%d ", no);
		}
		
		System.out.print("\nRearranged: ");
		for (int element : rearranged) {
			System.out.printf("%d ", element);
		}
	}
}