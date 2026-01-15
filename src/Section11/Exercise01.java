package Section11;

public class Exercise01 {
	// this will be executed in MainSectionXX-Class
	public static void run() {
		introductionFriend();
	}
	
	// this will be executed in the run method of this class
	public static void introductionFriend() {
		System.out.println("Hello! I'm your friend in the \"introductionFriend\"-Method.");
		System.out.println("I'll run the \"sayHello\"-Method from here: \n");
		sayHello();
	}
	
	// this will be executed in the "introductionFriend"-Method in this class
	public static void sayHello() {
		System.out.println("Hello there from the \"sayHello\"-Method!");
	}
}

