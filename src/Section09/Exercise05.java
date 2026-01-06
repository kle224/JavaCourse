package Section09;

public class Exercise05 {
	public static void run() {
		int[] numbers = {1,12,33,45,5,7,0};
		int sum = 0, max = numbers[0], min = numbers[0];
		
		for (int number : numbers) {
			sum += number;
			
			if (number > max)
				max = number;
			
			if (number < min)
				min = number;
		}
		System.out.printf("The sum of all array elements is: %d. %n", sum);
		System.out.printf("The greatest value is: %d. %n", max);
		System.out.printf("The smalles value is: %d. %n", min);
	}
}