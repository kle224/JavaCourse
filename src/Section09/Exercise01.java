package Section09;

public class Exercise01 {
	public static void run() {
		// 1. Declaring an array
        int[] numbers;
     // int[] numbers = new int[2]; ==> this will work too  

        // 2. Initializing an array with 'new' and the array size
        numbers = new int[2];

        numbers[0] = 10;
        numbers[1] = 20;

        // 3. Initializing an array with specific values
        int[] values = { 5, 15, 25, 35};

        // Output
        System.out.println(numbers[1]); // 20
        System.out.println(values[2]);  // 25
	}
}