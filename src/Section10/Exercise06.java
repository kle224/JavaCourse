package Section10;

import java.util.Scanner;
import java.util.Arrays;

public class Exercise06 {
	public static void run() {
		
		// Goal 1: sum of the primary diagonal (1,5,9)
		// Goal 2: sum of the secondary diagonal (3,5,7)
		
		// step: creating an 2D-Array based on input numbers for rows and columns
		// step: fill the array with ascending numbers, starting from 1
		// step: display the array based on the rows for readability
		// step: iterating through the array 
		
		int sumP = 0, sumS = 0, sum = 0; // primary sum, secondary sum and general sum
		int input;				 		 // input for rows and columns
		int filler = 1;					 // to fill up the array
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
		
		// iterating through the each row
		for (int row = 0; row < mat.length; row++) {
		// looking for the single sums of each diagonal:
			// iterating through each element of the row
			for (int no = 0; no < mat[row].length; no++) {
				// if the index of the current element is equal to the number of the row
				// then the element will be counted into the sum for the primary diagonal
				if (no == row) {
					sumP += mat[row][no];
				}

				// if the index of the current element is equal to:
				// term => the length of the row - the index of the current row
				// then the element will be counted into the sum for the secondary diagonal
				if (no == ((mat[row].length - 1) - row)) {
					sumS += mat[row][no];
				}
			}
		
		// looking for the sum of both diagonals:
			// iterating through each element of the row
			for (int no = 0; no < mat[row].length; no++) {
				// adding the value if the element it belongs to one or both diagonals.
				// the intersection is only added once due to the logical OR
				if (no == row || no == ((mat[row].length - 1) - row)) {
					sum += mat[row][no];
				}
			}
		}
		
		System.out.printf("Primary sum is: %d. %n", sumP);
		System.out.printf("Secondary sum is: %d. %n", sumS);
		System.out.printf("General sum is: %d. %n", sum);
		
		scan.close();
	}
}