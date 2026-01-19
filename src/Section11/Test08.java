package Section11;

// Was brauchen wir alles?
// Mail splitten
// Nach "@" und "." suchen
// sicherstellen, dass bestimmte Zeichen ("@") nur einmal vorkommen

public class Test08 {
	public static void run() {
		String eMail = "noah@mail.com";
		
		boolean isValid = validate(eMail);
		System.out.println("Mail is valid: " + isValid);
	}
	
	public static boolean validate(String eMail) {
		Mail check1 = searchAt(eMail, "@");
		
		if (check1.bool) {
			Mail splitted = splitMail(eMail, "@");
			
			Mail check2 = searchItem(splitted.part2, ".");
			
			if (check2.bool) {
				return true;
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
			if (itemCounter == 1) {
				System.out.println("Only 1 \"@\"!");
				return new Mail(true, itemCounter, index); // returns bool = true, number (count of @s) and index = i
			} else {
				System.out.println("There are too many \"@\"s.");
				return new Mail(false);
			}
		} else {
			System.out.println("There is no \"@\".");
			return new Mail(false);
		}
	}
	
	// Teilaufgabe: Item (".") suchen
	public static Mail searchItem(String part2, String item) {
		char[] elements = part2.toCharArray();
		boolean isThere = false;
		
		for (char element : elements) {
			if (item.equals(Character.toString(element))) {
				isThere = true;
			}
		}
		
		return new Mail(isThere);
	}
	
	// Teilaufgabe: prüfen, ob im Teil hinter dem @ mindestens ein "." ist
	public static Mail searchDot(String part2, String item) {
		return new Mail();
	}
	
	// Teilaufgabe: beim "@" splitten
	public static Mail splitMail(String eMail, String character) {
		String[] parts = eMail.split(character);
		String part1 = parts[0];
		String part2 = parts[1];
		
		return new Mail(part1, part2);
	}
}


// Object Mail
class Mail {
	int number, index;
	String part1, part2, complete;
	boolean bool;
	
	Mail(boolean bool, int index) {
		this.bool = bool;
		this.index = index;
	}
	
	Mail(boolean bool, int number, int index) {
		this.bool = bool;
		this.number = number;
		this.index = index;
	}
	
	Mail(String part1, String part2) {
		this.part1 = part1;
		this.part2 = part2;
	}
	
	Mail (boolean bool) {
		this.bool = bool;
	}
	
	// empty object for easy coding
	Mail() {
	}
}