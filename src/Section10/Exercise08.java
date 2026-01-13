package Section10;

import java.util.ArrayList;

public class Exercise08 {
	public static void run() {
		// declaring a String ArrayList called fruits
		ArrayList<String> fruits = new ArrayList<String>();
		
		// adding elements into the ArrayList using the "add Method"
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Raspberry");
		fruits.add("Raspberry");
		
		// print the entire ArrayList
		System.out.println("String ArrayList \"fruits\": " + fruits);
		
		// accessing the first element of the ArrayList (index 0) using the "get Method"
		System.out.println("\nOriginal 1st element: " + fruits.get(0));
		
		// modifying an element using the "set Method"
		fruits.set(0, "Orange");
		
		// print the changed element
		System.out.println("Changed 1st element: " + fruits.get(0));
		
		// determining the size of the ArrayList using the "size Method"
		System.out.println("\nInitial Size: " + fruits.size());
		
		// removing an element using the "remove Method"
//		fruits.remove("Raspberry");	// will remove the element "Raspberry" (only one if there are more!)
		fruits.remove(3);			// will remove the 4th element of the array
		
		// print the size after removing items
		System.out.println("Size after removal: " + fruits.size());
		
		// iterating through an ArrayList with an enhanced for loop
		System.out.println("\nIterating each element:");
		for (String fruit : fruits) {
			System.out.println(fruit);
		}
		
		// check if an element exists using the "contains Method"
		System.out.println("\nBoolean return of ArrayList.contains: "
		+ fruits.contains("Banana") + ".");
		
		// clearing the entire ArrayList with the "clear Method"
		fruits.clear();
	}
}