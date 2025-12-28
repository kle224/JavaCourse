package Section06;

public class Exercise04 {
	public static void run() {
		boolean a = true, b = false;
		int c = 5, d = 10;
		
		// double ampersand => LOGICAL AND
		boolean result = a && b; // can only be true if a and b are true
		System.out.println("Result after double ampersand (&&): " + result);
		
		// double pipe => LOGICAL OR
		result = a || b;
		System.out.println("Result after double pipe (||): " + result);
		
		// Not operator
		result = !a || b;
		System.out.println("A is: " + a + " and !A is: " + !a);
		System.out.println("B is: " + b + " and !B is: " + !b);
		System.out.println("==> " + result);
		
		// short circuit logical operator
		if ((c > 0) && (d > c)) {
			System.out.println("D is greater than C!");
		}
	}
}
