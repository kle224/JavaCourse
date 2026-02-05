package Section12;

public class Exercise04 {
	public static void run() {
		makeSandwich();
	}
	
	public static void buyBread() {
		System.out.println("Bought two slices of bread.");
	}
	
	public static void getSlicesOfBread() {
		buyBread();
		System.out.println("Got two slices of bread.");
	}
	
	public static void spreadPeanutButter() {
		System.out.println("Spread peanutbutter on one slice.");
	}
	
	public static void spreadJelly() {
		System.out.println("Spread jelly on the other slice.");
	}
	
	public static void putSlicesTogether() {
		System.out.println("Put both slices together.");
	}
	
	public static void makeSandwich() {
		getSlicesOfBread();
		spreadPeanutButter();
		spreadJelly();
		putSlicesTogether();
		
		System.out.println("Sandwich is ready!");
	}
}