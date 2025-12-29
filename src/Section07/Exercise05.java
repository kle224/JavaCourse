package Section07;

public class Exercise05 {
	
	public enum Day{
		Monday, Tuseday, Wednesday, Thursday, Friday, Saturday, Sunday
	}
	
	public enum CoffeeSize{
		Small, Medium, Large, Venti
	}
	
	public static void run() {
		
		CoffeeSize size = CoffeeSize.Medium;
		double price;
		
		switch (size) {
		case Small:
			price = 2.50;
			break;
		case Medium:
			price = 3.00;
			break;
		case Large:
			price = 3.50;
			break;
		case Venti:
			price = 4.20;
			break;
		default:
			price = 420.99;
			break;
		}
		
		System.out.printf("The price for your coffee is: $%.2f. %n", price);
	}
}


