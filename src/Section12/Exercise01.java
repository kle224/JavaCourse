package Section12;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise01 {
	public static void run() {
		int input = 0;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please enter you number: ");
		input = scan.nextInt();
		
		if (input <= 0) {
			System.out.println("You have to choose a number greater 0!");
		} else {
			int[] fib = new int[input];
			fib = initialFib(fib);
			
			System.out.println("\nYour fibonaccis are: ");
			System.out.println(Arrays.toString(fib));
		}
		
		scan.close();
	}
	
	public static int[] initialFib(int[] fib) {
		if (fib.length == 1) {
			fib[0] = 0;
			return fib;
		} else if (fib.length == 2) {
			fib[0] = 0;
			fib[1] = 1;
			return fib;
		} else {
			fib[0] = 0;
			fib[1] = 1;
			fib = fibonacciR(fib, 2);
			return fib;
		}
	}
	
	public static int[] fibonacciR(int[] fib, int n) {
		if (n < fib.length) {
			fib[n] = fib[n-2] + fib[n-1];
			n++;
			fibonacciR(fib, n);
		}
		
		return fib;
	}
}