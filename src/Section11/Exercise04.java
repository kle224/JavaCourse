package Section11;

public class Exercise04 {
	public static void run() {
		int originalNumber = 10;
		int[] originalNumbers = {1,2,3,4,5};
		String firstName = "Joe";
	
		// passing "originalNumber" as a (VALUE) parameter into the modifyPrimitive-Method
		// this is pass by value:
		System.out.println("Outside Method, number is (before calling): " + originalNumber);
		modifyPrimitive(originalNumber);
		System.out.println("Outside Method, number is (after calling): " + originalNumber);
		System.out.println("");
		
		// passing "firstName" as a (VALUE) parameter into the changeName-Method
		// this is pass by value:
		System.out.println("Outside Method, name is (before calling): " + firstName);
		changeName(firstName);
		System.out.println("Outside Method, name is (after calling): " + firstName);
		System.out.println("");
		
		// passing "originalNumbers" as a (REFERENCE) parameter into the modifyArray-Method 
		// this is pass by reference:
		System.out.println("Outside Method (before calling the method), array[0] is: " + originalNumbers[0]);
		modifyArray(originalNumbers);
		System.out.println("Outside Method (after calling the method), array[0] is: " + originalNumbers[0]);
	}
	
	public static void modifyPrimitive(int number) {
		number = 20;
		System.out.println("Inside Method, number is: " + number);
	}
	
	public static void changeName (String name) {
		name = "Frankie";
		System.out.println("Your name inside the Method is: " + name);
	}
	
	public static void modifyArray(int[] array) {
		array[0] = 99;
		System.out.println("Inside Method, array[0] is: " + array[0]);
	}
}