package Section12;

public class Exercise05 {
	public static void run() {
		String input = "abcd";
		
		System.out.println("String @ start: " + input);
		
		String output = reverseString(input);
		
		System.out.println("String @ end: " + output);
	}
	
	public static String reverseString(String input) {
		// base case
		if (input.isEmpty() || input.length() == 1) {
			return input;
		}
		
		//recursive case
		return input.charAt(input.length() - 1) + reverseString(input.substring(0, input.length() - 1));	
	}
}