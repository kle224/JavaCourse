package Section05;

public class MainSection05 {
	public static void main (String []args) {
		
		// Section Info: Setting up the Java Development Environment
		
		// Hier werden Klassen aus dem selben Package aufgerufen

//		System.out.println("1:");
//		// - shows the usage of String.format inside of "System.out.println"
//		MagicPotionCalculator task01 = new MagicPotionCalculator();
//		task01.run();

//		System.out.println("2:");
//		// - fills different variables with user input and calculating with them
//		// - prints the result based on the value amount
//		BudgetTrackerPlus task02 = new BudgetTrackerPlus();
//		task02.run();

		System.out.println("3:");
		// - just a few variables to try out refactoring
		RefactoringExample task03 = new RefactoringExample();
		task03.run();
		
		/*
		 * Hier werden Klassen aus einem anderen Package importiert und dann aufgerufen:
		 * 
		 * package Section06;
		 * 
		 * import Section05.MagicPotionCalculator;
		 * 
		 * public class MainSection06 {
		 * 		public static void main(String[] args) {
		 * 
		 * 		MagicPotionCalculator calculator = new MagicPotionCalculator();
		 * 		calculator.run();
		 * 	}
		 * }
		 */
		/////////////////////////////////////////////////////////////////////////////////////
		/*
		 * Hier wird eine Klasse aus einem anderen Projekt importiert um später aufgerufen zu werden:
		 * 
		 * import AnderesProjekt.Section05.MagicPotionCalculator;
		 */
	}
}
