package Section07;

public class Exercise02 {
	public static void run() {
		int day = 2;
		String dayName, ending;
		
		switch (day) {
		case 1:
			dayName = "Monday";
			ending = "st";
			break;
		case 2:
			dayName = "Tuseday";
			ending = "nd";
			break;	
		case 3:
			dayName = "Wednesday";
			ending = "rd";
			break;	
		case 4:
			dayName = "Thursday";
			ending = "th";
			break;	
		case 5:
			dayName = "Friday";
			ending = "th";
			break;	
		case 6:
			dayName = "Saturday";
			ending = "th";
			break;
		case 7:
			dayName = "Sunday";
			ending = "th";
			break;
		default:
			dayName = "N/A";
			ending = "";
			break;
		}
		
		System.out.printf("The %d%s day of the week is called \"%s\".%n", day, ending, dayName);
	}
}
