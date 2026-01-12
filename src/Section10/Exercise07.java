package Section10;

import java.util.Scanner;

public class Exercise07 {
	public static void run() {
		int sum = 0;		// sum of both diagonals
		int input;			// input for rows and columns
		int filler = 1;		// to fill up the array
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please enter how much rows and columns you want to have: ");
		input = scan.nextInt();
		
		int[][] mat = new int[input][input];
		
		// fill the array
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j]= filler++; 
			}
		}
		
		// display the array
		System.out.println("\nDisplay array: ");
		for (int[] row : mat) {
			for (int element : row) {
				
				switch (String.valueOf(Math.abs(input)).length()) {
				case 1, 2:
					System.out.printf("%02d ", element);
					break;
				case 3:
					System.out.printf("%03d ", element);
					break;
				case 4:
					System.out.printf("%04d ", element);
					break;
				case 5:
					System.out.printf("%05d ", element);
					break;
				case 6:
					System.out.printf("%06d ", element);
					break;
				default:
					break;
				}
			}
			System.out.println("");
		}
		System.out.println("");
		
		for (int row = 0; row < mat.length; row++) {
				for (int no = 0; no < mat[row].length; no++) {
					// adding the value if the element it belongs to one or both diagonals.
					// the intersection is only added once due to the logical OR
					if (no == row || no == ((mat[row].length - 1) - row)) {
						sum += mat[row][no];
					}
				}
			}
		System.out.printf("General sum is: %d. %n", sum);
			
		scan.close();
	}
}