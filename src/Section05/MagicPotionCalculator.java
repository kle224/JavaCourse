package Section05;

public class MagicPotionCalculator {
	public static void run() {
		int magicEssence = 5, dragonScale = 3, unicornHair = 2;
		int potionPower = magicEssence * dragonScale + unicornHair;
		
		System.out.println(String.format("Testing with String.format: %d", potionPower));
		System.out.println(String.format("Another test: %d", magicEssence));
		
		System.out.println("The power of the mixture is: " + potionPower);
	}
}
