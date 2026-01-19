package Section11;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import jdk.internal.net.http.common.SequentialScheduler.RestartableTask;

public class Exercise08 {
	public static void run() {
		Scanner scan = new Scanner(System.in);
		Random rnd = new Random();
		
		System.out.print("Please enter your email: ");
		isValidMail(scan.nextLine());
		
	}
	
	public static void isValidMail(String mail) {
		char[] elements = mail.toCharArray();
		
		Result at = countCharacter(mail, '@');
		System.out.println("Character counter: " + at.number);
	}
	
	// muss einmal für @ und einmal für . gemacht werden
	public static Result countCharacter(String mail, char searched) {
		char[] elements = mail.toCharArray();
		int searchedCounter = 0, index;
		boolean valid = true;
		
		for (int i = 0; i < elements.length; i++) {
			if (searched == elements[i]) {
				searchedCounter++;
				
				if (searchedCounter == 1) {
					index = elements[i];
				}
			}
		}
		
		if (searchedCounter > 0) {
			return new Result(valid = true, searchedCounter, index);
		} else {
			return new Result(valid = false);
		}
		
		return new Result(valid, searchedCounter);
	}
	
	// Call with string, '@'
	public static Result checkAt(String validate, char item) {
		char[] elements = validate.toCharArray();
		
		// imports the return of countCharacter() into a new Result-object
		Result valid = (countCharacter(validate, item));
		
		if (valid.bool) {
			if (valid.number == 1) { // bei nur einem @
				return new Result(valid.bool, valid.number, valid.index);
			} else {				// wenn mehr als ein @ da ist
				return new Result(false);
			}
		} else {
			return new Result(false);
		}
	}
	
	public static void checkDot(String validate, char item) {
	}
	
	public static void checkLength(String validate) {
	}
	
	public static Result splitMail(String mail, int index) {
		
		return new Result(true);
	}
}















// Klasse geschrieben die ein Objekt enthält, welches in der Methode searchCharacter genutzt wird um mehrere
// Werte zurückzugeben
class Result {
	boolean bool;
	int number, index;
	char character;
	String part1, part2;
	
	// Constructor der Klasse Result ("this" bezieht sich auf das aktuelle Objekt, als das,
	// das gerade erstellt wird)
	Result(boolean bool, int number, char character, String part1, String part2) {
		this.bool = bool;			//
		this.number = number;		// anzahl der Trennzeichen
		this.character = character; // delimiter (Trennzeichen)
		this.part1 = part1;			// part vor dem delimiter
		this.part2 = part2;			// part nach dem delimiter
	}
	
	// Constructor for bool and both int returns
	Result(boolean bool, int number, int index) {
		this.bool = bool;
		this.number = number;
		this.index = index;
	}
	
	// Constructor for bool and int return
		Result(boolean bool, int index) {
			this.bool = bool;
			this.index = index;
		}
	
	// Constructor for bool return only
	Result(boolean bool) {
		this.bool = bool;
	}
}



////////////////////////////////////////////
