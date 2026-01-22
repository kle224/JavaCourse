package Section11;

import java.util.Scanner;

public class Exercise08 {
	public static void run() {
		boolean loop = true;
		String mail = "";
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.print("Please enter the email you want to validate: ");
			mail = scan.nextLine();
			Mail validation = validate(mail);
			
			if (validation.bool) {
				System.out.println("Mail is valid!\n");
			} else {
				System.out.println("Mail is invalid because of: "
				+ validation.explanation + ".\n");
			}
			
			System.out.println("Do you want to try another mail?");
			System.out.print("Enter any key for yes or \"x\" to quit and press enter: ");
			loop = cont(scan.nextLine());
			System.out.println("");
		} while (loop);
		
		scan.close();
	}
	// carries the validation logic
	public static Mail validate(String content) {
		int eCode = 0;
		System.out.println("Validate: " + content);
		
		// search "@"
		Mail check1 = searchAT(content, "@");
		eCode = check1.eCode;
		
		if (check1.bool) {
			// @ passt
			Mail check2 = searchItem(content, " ");
			
			if (!check2.bool) {
				// spaces passt
				Mail splitted = splitString(content, "@");
				Mail check3 = checkLength(splitted.part1);
				
				if (check3.bool) {
					// checkLength passt
					Mail check4 = searchItem(splitted.part2, ".");
					
					if (check4.bool) {
						// punkte passen
						Mail check5 = checkIndex(splitted.part2, ".");
						
						if (!check5.bool) {
							// indices passen
							Mail result = explain(eCode, "");
							return new Mail(result.bool, result.explanation);
						} else {
							// indices raus
							Mail result = explain(check5.eCode, "");
							return new Mail(result.bool, result.explanation);
							}	
					} else {
						// punkte raus
						Mail result = explain(check4.eCode, ".");
						return new Mail(result.bool, result.explanation);
						}
				} else {
					// checkLength raus
					Mail result = explain(check3.eCode, "");
					return new Mail(result.bool, result.explanation);
					}
			} else {
				// spaces raus
				Mail result = explain(check2.eCode, " ");
				return new Mail(result.bool, result.explanation);
				}
		} else {
			// @ passt raus
			Mail result = explain(check1.eCode, "");
			return new Mail(result.bool, result.explanation);
		}
	}
	
	// search and count "@"-items
	public static Mail searchAT(String content, String item) {
		char[] elements = content.toCharArray();
		int itemCounter = 0;
		
		for (char element : elements) {
			if (item.equalsIgnoreCase(Character.toString(element))) {
				itemCounter++;
			}
		}
		
		if (itemCounter == 1) {
			return new Mail(true, 0);
		} else if (itemCounter == 0) {
			return new Mail(false, 2);
		} else {
			return new Mail(false, 1);
		}
	}
	
	// searching specific items
	public static Mail searchItem(String content, String item) {
		char[] elements = content.toCharArray();
		int eCode = 4;
		boolean isThere = false;
		
		for (int i = 0; i < elements.length; i++) {
			if (item.equalsIgnoreCase(Character.toString(elements[i]))) {
				eCode = 3;
				isThere = true;
			}
		}
		
		if (item.equalsIgnoreCase(".") && eCode == 3) {
			eCode = 0;
		} else if (item.equalsIgnoreCase(" ") && eCode == 4) {
			eCode = 0;
		}
		
		return new Mail(isThere, eCode);
	}
	// split a string
	public static Mail splitString(String content, String delimiter) {
		String[] split = content.split(delimiter);
		String part1 = split[0];
		String part2 = split[1];
		
		return new Mail(part1, part2);
	}
	// check length of a string
	public static Mail checkLength(String content) {
		if (content.length() > 0) {
			return new Mail(true, 0);
		} else {
			return new Mail(false, 6);
		}
	}
	
	// check 1st and last index
	public static Mail checkIndex(String content, String item) {
		char[] elements = content.toCharArray();
		int index = elements.length - 1;
		
		if (item.equalsIgnoreCase(Character.toString(elements[0])) ||
			item.equalsIgnoreCase(Character.toString(elements[index]))) {
			return new Mail(true, 5);
		} else {
			return new Mail(false, 0);
		}
	}
	
	// converts the error code into a string based statement
	public static Mail explain(int eCode, String item) {
		boolean valid;
		String explanation;
		
		switch (eCode) {
		case 0:
			explanation = "There is no error!";
			valid = true;
			break;
		case 1:
			explanation = "You can only use one \"@\".";
			valid = false;
			break;
		case 2:
			explanation = "You need to use an \"@\" in your email.";
			valid = false;
			break;
		case 3:
			explanation = "The element \"" + item + "\" exists.";
			valid = false;
			break;
		case 4:
			explanation = "A searched element \"" + item + "\" don't exist.";
			valid = false;
			break;
		case 5:
			explanation = "You need another letters right after the \"@\" or "
			+ "at the end of your email.";
			valid = false;
			break;
		case 6:
			explanation = "You can't start your email with an \"@\".";
			valid = false;
			break;
		default :
			explanation = "There is an unknown error.";
			valid = false;
			break;
		}
		
		return new Mail(valid, explanation);
	}
	
	// return a boolean dependend to an input to continue the loop
	public static boolean cont(String input) {
		if (input.equalsIgnoreCase("x")) {
			System.out.println("Goodbye!");
			return false;
		} else {
			return true;
		}
	}
}



class Mail {
int eCode;
boolean bool;
String part1, part2, explanation;
// constructor for splitted strings
Mail(String part1, String part2) {
this.part1 = part1;
this.part2 = part2;
}
// constructor for boolean impression and an error code
Mail(boolean bool, int eCode) {
this.bool = bool;
this.eCode = eCode;
}
// constructor for boolean impression and an explanation for the error
Mail(boolean bool, String explanation) {
this.bool = bool;
this.explanation = explanation;
}
// constructor with boolean impression for a valid string
Mail(boolean bool) {
this.bool = bool;
}
// constructor for placeholder
Mail() {
}
// ErrorCode Legend:
// 0. none
// 1. more than 1 @
// 2. no @
// 3. element exist in the string
// 4. element doesn't exist in the string
// 5. wrong item on 1st or last slot
// 6. string length
}