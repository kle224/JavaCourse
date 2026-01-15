package Section11;

public class Exercise02 {
	public static void run() {
		sayHello("Frankie", 71); // passing "Frankie" and "71" as parameters into the sayHello-Method
		sayHello("Margie", 68);  // passing "Margie" and "68" as parameters into the sayHello-Method
	}
	
	// creating a Method that uses the parameters name (of type String) and age (of type int)
	public static void sayHello(String name, int age) {
		System.out.printf("Hello from the Method \"sayHello\", %s! %n", name);
		System.out.printf("Is it true that you are %d years old? %n", age);
		System.out.println("");
	}
}