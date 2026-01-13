package Section10;

import java.util.ArrayList;

//import java.util.ArrayList;

public class Exercise09 {
	public static void run() {
		// declaring the ArrayList
		ArrayList<String> fruits = new ArrayList<String>(); // typing "ArrayList" + Enter
														   // automatically imports the class
		// adding elements into the ArrayList
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Cherry");
		fruits.add("Dates");
		
		// display all elements of the ArrayList
		System.out.println("Display all elements of the ArrayList:");
		for (String fruit : fruits) {
			System.out.println("Current fruit: " + fruit);
		}
		
		// display a specific element of the ArrayList
		System.out.println("\nDisplay a specific element of the ArrayList: " + fruits.get(0));
		
		// creating a boolean value based on whether an element is in the ArrayList or not
		boolean isItThere = fruits.contains("Apple");
		System.out.println("\nIs it there? " + isItThere + ".");
		
		// get the current size of the ArrayList
		int length = fruits.size();
		System.out.println("\nCurrent length of the ArrayList: " + length + ".");
		
		// remove elements from the ArrayList
		fruits.remove("Banana"); // removing an element called "Banana"
		fruits.remove(0);		 // removing the first element of the ArrayList
		
		// print the current ArrayList (after removing elements)
		System.out.println("\nArrayList after removing elements: " + fruits);
		
		// adding new elements in with different Methods
		fruits.addFirst("Raspberry");	// adding an element on the 1st slot of the ArrayList
		fruits.add(2, "Coconut");		// adding an element on a specific slot
		
		// print the current ArrayList (after adding elements)
		System.out.println("ArrayList after adding elements: " + fruits);
	}
}