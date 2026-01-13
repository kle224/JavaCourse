package Section10;

import java.util.ArrayList;

public class Exercise08 {
	public static void run() {
		// declaring a String arraylist called fruits
		ArrayList<String> fruits = new ArrayList<String>();
		
		// adding elements into the list using the "add Method"
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Raspberry");
		fruits.add("Raspberry");
		
		// print the entire list
		System.out.println("String ArrayList \"fruits\": " + fruits);
		
		// accessing the first element of the list (index 0) using the "get Method"
		fruits.get(0);
		
		// print the accessed element
		System.out.println("Original 1st element: " + fruits.get(0));
		
		// modifying an element using the "set Method"
		fruits.set(0, "Orange");
		
		// print the changed element
		System.out.println("Changed 1st element: " + fruits.get(0));
		
		// determining the size of the arraylist using the "size Method"
		fruits.size();
		
		// print the size before removing anything
		System.out.println("Initial Size: " + fruits.size());
		
		// removing an element using the "remove Method"
//		fruits.remove("Raspberry");	// will remove the element "Raspberry"
		fruits.remove(2);			// will remove the 3rd element of the array
		
		// print the size after removing items
		System.out.println("Size after removal: " + fruits.size());

		// print the entire list
		System.out.println("Current ArrayList \"fruits\": " + fruits);
	}
}