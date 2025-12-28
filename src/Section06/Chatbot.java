package Section06;

import java.util.Scanner;

public class Chatbot {
	public static void run() {
		// Step 1: Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);
        boolean status = true;
        int counter = 0;
        
        while (status) {
        	// Step 2: Prompt the user for input
            System.out.println("Please enter something: ");
            String userInput = scanner.nextLine().toLowerCase(); // Read user input
     
            // Step 3: Process user input using conditional statements
            if (userInput.contains("hello") || userInput.contains("hi") || userInput.contains("hey")) {
                System.out.println("Chatbot: Hello! How can I help you today?");
            } else if (userInput.contains("how are you") || userInput.contains("how is it going")) {
                System.out.println("Chatbot: I'm just a bot, but I'm here to help you!");
            } else if (userInput.contains("weather") && userInput.contains("like")) {
                System.out.println("Chatbot: I don’t have real-time weather data, but it's always sunny in the digital world!");
            } else if (userInput.contains("bye") || userInput.contains("goodbye")) {
                System.out.println("Chatbot: Goodbye! Have a great day!");
                status = false;
            } else {
            	counter++;
            	
            	if (counter < 3) {
                    System.out.println("Chatbot: I'm sorry, I don't understand that.");
            	} else {
            		System.out.println("Too many failed attempts...");
            		status = false;
            	}
            }
        }
 
        
        // Step 4: Close Scanner
        scanner.close();
	}
}
