package Section07;

import java.util.Scanner;

public class Exercise04 {
	public static void run() {
		Scanner scan = new Scanner(System.in);
		String season;
		
		// switch statements for strings
		
		System.out.print("Please enter the current season of the year: ");
		season = scan.nextLine();
		
		switch (season.toLowerCase()) {
		case "spring":
			System.out.println(season + ": The flowers are blooming and everything is nice :)");
			break;
		case "summer":
			System.out.println(season + ": It's sunny and maybe very hot.");
			break;
		case "autumn":
			System.out.println(season + ": The leaves are falling and it's windy sometimes.");
			break;
		case "winter":
			System.out.println(season + ": It's mostly cold and sometimes snowy.");
			break;
		default:
			System.out.println("There is no season like this.");
			break;
		}

		scan.close();
	}
}
