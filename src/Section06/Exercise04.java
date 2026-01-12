package Section06;

public class Exercise04 {
	public static void run() {
		boolean a = true, b = false;
		int c = 5, d = 10;
		
		// prints current 
		System.out.println("Current values:");
		System.out.printf("Bools: %nA: %b || B: %b %n", a, b);
		System.out.printf("Ints: %nC: %d || D: %d %n%n", c, d);
		
		// double ampersand => LOGICAL AND
		boolean result = a && b; // can only be true if a and b are true
		System.out.println("Result after double ampersand (&&): " + result + "\n");
		
		// double pipe => LOGICAL OR
		result = a || b;
		System.out.println("Result after double pipe (||): " + result + "\n");
		
		// Not operator
		result = !a || b;
		System.out.println("A is: " + a + " and !A is: " + !a);
		System.out.println("B is: " + b + " and !B is: " + !b);
		System.out.println("==> " + result + "\n");
		
		// short circuit logical operator
		if ((c > 0) && (d > c)) {
			System.out.println("D is greater than C!");
		}
	}
}
