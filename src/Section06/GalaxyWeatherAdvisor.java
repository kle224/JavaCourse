package Section06;

import java.util.Scanner;

public class GalaxyWeatherAdvisor {
	public static void run() {
		Scanner scan = new Scanner(System.in);
		 
        // Step 2: Display a welcome message
        System.out.println("Welcome to the Galactic Weather Advisor!");
        System.out.println("Please enter the planet you are on (Earth, Mars, Venus, Jupiter):");
        
        // Step 3: Take user input for planet and convert to lowercase
        String planet = scan.nextLine();
 
        // Step 4: Take current temperature as input
        System.out.println("Please enter the current temperature in Celsius:");
        double temp = scan.nextDouble();
 
        // Step 5: Declare a variable to store response
        String advice = "";
        
        // Step 6: Compute response based on planet and temperature
        if (planet.equalsIgnoreCase("earth") || planet.equalsIgnoreCase("mars") || planet.equalsIgnoreCase("venus")) {
            if (temp <= 42.0 && temp > 0.0) {
            	advice = temp + "°C is normal weather on " + planet + " you can wear normal clothes based on your preferences.";
            } else if (temp > 42.0) {
            	advice = temp + "°C is very hot on " + planet + " you should avoid going outside.";
            } else if (temp < 0.0) {
            	advice = temp + "°C is very cold on " + planet + " you should use special clothing.";
            }
        } else {
        	System.out.printf("Your planet \"%s\" is not on our list...", planet);
        }
 
        // Step 7: Display response
        System.out.println(advice);
 
        // Step 8: Close Scanner
        scan.close();
	}
}
