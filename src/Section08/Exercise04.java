package Section08;

import java.util.Scanner;

public class Exercise04 {
	public static void run() {
		int numbers, stored;
		int fib1 = 0, fib2 = 1;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please tell us how many fibonaccis you want: ");
		numbers = scan.nextInt();
		
		if (numbers == 1) {
			System.out.println("Your fibonaccis are: ");
			System.out.printf("%d", fib1);
		} else if (numbers > 1){
			System.out.println("Your fibonaccis are: ");
			for (int i = 0; i < (numbers - 1); i++) {
				if (i == 0) {
					System.out.printf("%d %d ", fib1, fib2);
					stored = fib1;
					fib1 = fib2;
					fib2 = stored + fib2;
				} else {
					System.out.printf("%d ", fib2);
					stored = fib1;
					fib1 = fib2;
					fib2 = stored + fib2;
				}
			}
		} else {
			System.out.println("You need to use numbers bigger or equal \"1\".");
		}
		
		scan.close();
	}
}
