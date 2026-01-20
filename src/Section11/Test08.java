package Section11;

import java.util.Scanner;


public class Test08 {
	public static void run() {
		boolean check = true;
		
		Scanner scan = new Scanner(System.in);
		
		while (check) {
			System.out.print("Please enter your e-mail: ");
			String email = scan.nextLine();
			
			boolean isValid = validate(email);
			
			if (isValid) {
				System.out.println("Your mail is valid!");
				check = false;
			} else {
				System.out.println("Your mail isn't valid. Please try it again or quit.");
				System.out.print("Press any key + Enter to try it again or \"x\" to quit: ");
				check = cont(scan.nextLine(), "x");
			}
		}
	}
	
	public static boolean validate(String eMail) {
		Mail check1 = searchAt(eMail, "@");
		
		if (check1.bool) {
			Mail check2 = searchItem(eMail, " ");
			
			if (!check2.bool) { // false muss hier richtig sein, kein Space erlaubt!
				Mail splitted = splitMail(eMail, "@");
				
				if (splitted.part1.length() > 0) { // check if part1 has at least 1 character
					Mail check3 = searchItem(splitted.part2, ".");
					
					if (check3.bool) { // check if part2 has at least 1 dot
						Mail check4 = checkIndex(splitted.part2, "."); // should be false!
						
						if (!check4.bool) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	// Teilaufgabe: "@" suchen und validieren, ob es nur eins ist
	public static Mail searchAt(String eMail, String item) {
		char[] elements = eMail.toCharArray();
		int itemCounter = 0, index = 0;
		boolean isThere = false;
		
		for (int i = 0; i < elements.length; i++) {
			if (item.equalsIgnoreCase(Character.toString(elements[i]))) {
				isThere = true;
				itemCounter++;
				
				if (itemCounter == 1) {
					index = i;
				}
			}
		}
		
		if (isThere) {
			if (itemCounter == 1) { // case 1 @
				return new Mail(true, "none"); // returns bool = true, number (count of @s) and index = i
			} else { // case more than 1 @
				return new Mail(false, "@>0");
			}
		} else { // case less than 1 @
			return new Mail(false, "@=0");
		}
	}
	
	// Teilaufgabe: Item (".", " ") suchen
	public static Mail searchItem(String content, String item) {
		char[] elements = content.toCharArray();
		boolean isThere = false;
		String problem = "elementNE";
		
		for (char element : elements) {
			if (item.equals(Character.toString(element))) {
				isThere = true;
				problem = "elementE";
			}
		}
		
		return new Mail(isThere, problem);
	}
	 
	// Teilaufgabe: beim "@" splitten
	public static Mail splitMail(String eMail, String character) {
		String[] parts = eMail.split(character);
		String part1 = parts[0];
		String part2 = parts[1];
		
		return new Mail(part1, part2);
	}
	
	// Teilaufgabe: prüfen ob der erste oder letzte Slot ein bestimmtes Zeichen (".") ist
	public static Mail checkIndex(String content, String item) {
		char[] elements = content.toCharArray();
		Mail indices = new Mail(false, "none");
		
		
		for (int i = 0; i < elements.length; i++) {
			if (i == 0 || i == (elements.length - 1)) {
				if (item.equals(Character.toString(elements[i]))) {
					indices.bool = true;
					indices.problem = "indexErr";
				}
			}
		}
		
		return indices;
	}
	
	// returns a boolean true if input matches targetState
	public static boolean cont(String input, String targetState) {
		if (input.equalsIgnoreCase(targetState)) {
			return false;
		} else {
			return true;
		}
	}
}


// Object Mail
class Mail {
	int number, index;
	String part1, part2, problem;
	boolean bool;
	
	Mail(String part1, String part2) {
		this.part1 = part1;
		this.part2 = part2;
	}
	
	Mail (boolean bool, String problem) {
		this.bool = bool;
		this.problem = problem;
	}
}


// Idee für später:
// Object-String "problem" implementiert => Switch Statement schreiben, das mit diesem Wert
// analysiert, wo der Fehler liegt.