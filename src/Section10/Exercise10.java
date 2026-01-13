package Section10;

import java.util.Scanner;

public class Exercise10 {
	public static void run() {
		// Goals
		// 1. Store astronaut details with arrays => name, age, mission status
		// 2. Allow users to add new astronauts with their details
		// 3. Enables users to update an astronaut's mission status
		// 4. Allow users to display all astronauts and their current details
		// 5. Handle edge cases like missing astronauts and empty lists
		
		// initialize needed variables and arrays
		String[] names = new String[10]; // max 10 astronauts
		int[] age = new int[10];
		String[] missionStatus = new String[10];
		
		int astronautCounter = 0;		// to count the number of astronauts
		int digits = 20;				// for a clean sheet
		int current = 0;				// for usage of special elements
		Scanner scan = new Scanner(System.in);
		
		outerLoop:
		while (true) {
			System.out.println("Please choose an option:");
			System.out.println("1. Add an astronaut.");
			System.out.println("2. Update an astronauts mission status.");
			System.out.println("3. Display all astronauts and their missions.");
			System.out.println("4. Exit.");
			System.out.print("Please enter your choice: ");
			int choice = scan.nextInt();
			scan.nextLine();
			
			switchChoice:
			switch (choice) {
			case 1:
				if (astronautCounter == 10) {
					System.out.println("The list is full. There are already enough astronauts.");
					break;
				}
				System.out.print("\nPlease enter the name of the astronaut: ");
				names[astronautCounter] = scan.nextLine();
				System.out.print("Please enter the age: ");
				age[astronautCounter] = scan.nextInt();
				scan.nextLine(); // consumer
				System.out.print("Please enter the status of the mission: ");
				missionStatus[astronautCounter] = scan.nextLine();
				astronautCounter++;
				break;
			case 2:
				System.out.print("Please enter the name of the astronaut you want to update: ");
				String userInput = scan.nextLine();
				
				for (int i = 0; i <= astronautCounter; i++) {
					if (names[i] == userInput) {
						current = i;
						break;
					}
					
					if (current == 0 && i == (names.length - 1)) {
						System.out.println("Your astronaut is not in our list!");
						break switchChoice;
					}
				}
				
				System.out.print("Please enter the new status of the mission: ");
				missionStatus[current] = scan.nextLine(); 
				current = 0;
				break;
			case 3:
				System.out.println("Here is an overview of all our astronauts: \n");
				System.out.println("------------------------------------------");
				System.out.println("Name                | "
						+ "Age | Mission status"); // 20 characters for the name
				System.out.println("------------------------------------------");
				
				for (int i = 0; i < astronautCounter; i++) {
					System.out.print(names[i]);
					for (int spaces = 0; spaces < (digits - names[i].length()); spaces++) {
						System.out.print(" ");
					}
					System.out.printf("| %02d  | %s %n" , age[i], missionStatus[i]);
					System.out.println("------------------------------------------");
				}
				break;
			case 4:
				System.out.println("Goodbye!");
				break outerLoop;
			default:
				System.out.println("Your choice is invalid...");
				break;
			}
			
			System.out.print("\nPress any key to continue (\"x\" for exit) and press enter: ");
			char cont = scan.next().charAt(0);
			if (cont == 'x') {
				System.out.println("Goodbye!");
				break outerLoop; // current exit of the loop
			}
		}
		
		scan.close();
	}
}