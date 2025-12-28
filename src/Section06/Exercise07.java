package Section06;

public class Exercise07 {
	public static void run() {
		int score = 67;
		
		if (score >= 90) {
			System.out.println("Grade is A");
		} else if (score >= 85) {
			System.out.println("Grade is B");
		} else if (score >= 65) {
			System.out.println("Grade is C");
			score = 80;
		} else if (score >= 50) {
			System.out.println("Grade is D");
		} else if (score >= 30) {
			System.out.println("Grade is E");
		} else {
			System.out.println("Grade is F");
		}
	}
}
