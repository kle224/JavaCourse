package Section05;

import java.net.Socket;

public class MainSection05 {
	public static void main (String []args) {
		
		// Hier werden Klassen aus dem selben Package aufgerufen
		
//		MagicPotionCalculator task01 = new MagicPotionCalculator();
//		task01.run();
//		System.out.println("");
		
		BudgetTrackerPlus task02 = new BudgetTrackerPlus();
		
		
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
