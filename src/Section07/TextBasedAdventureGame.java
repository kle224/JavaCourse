package Section07;

import java.util.Scanner;

public class TextBasedAdventureGame {
	public static void run() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Hello and welcome to our text based adventure game! :)");
		String name, answer, direction1, direction2, action1, action2;
		
		System.out.print("Please enter your name, hero: ");
		name = scan.nextLine();
		
		System.out.printf("%nHello %s, hero of the shores! Welcome to the world of Deepheim.%n", name);
		
		System.out.println("A small village from the hills requests your help. A beasty dragon has stolen their gold");
		System.out.print("Can you help them to get their treasure back? ");
		answer = scan.nextLine();
		boolean game;
		
		switch (answer.toLowerCase()) {
		case "yes":
			game = true;
			break;
		case "yeah":
			game = true;
			break;
		case "yo":
			game = true;
			break;
		case "sure":
			game = true;
			break;
		default:
			game = false;
			break;
		}
		
		if (game) {
			System.out.print("Wonderful! Please decide the direction you want to go (left or right): ");
			direction1 = scan.nextLine();
			
			switch (direction1.toLowerCase()) {
			case "right":
				System.out.print("After a few miles you've found a dark path and the entry of a cove. "
						+ "Which way do you want to go (path or cove): ");
				direction2 = scan.nextLine();
				
				switch (direction2.toLowerCase()) {
				case "path":
					System.out.println("You follow the dark path.");
					System.out.println("A few very old crashed trees prevent you from following the path.");
					System.out.print("What do you want to do: burn them or chop them up with your axe? (brun or chop): ");
					action1 = scan.nextLine();
					
					switch (action1.toLowerCase()) {
					case "burn":
						System.out.println("Oh nooooo! The fire you startet burns down the entire forest...");
						System.out.println("The dragon noticed you and started the attack...");
						System.out.print("What do you want to do? Fight him or run away? (fight or run): ");
						action2 = scan.nextLine();
						
						switch (action2.toLowerCase()) {
						case "fight":
							System.out.println("You've been killed by the dragon... :(");
							break;
						case "run":
							System.out.println("You ran deeper into the burning forrest. "
									+ "Luckily, because of the smoke the dragon can't find you.");
							System.out.println("Inadvertently you find the tree stump where the dragon hides his treasures.");
							System.out.println("When you returned it to the villagers, they rewarded you with half the treasure. :)");
							break;
						default:
							System.out.println("Invalid Option...");
							break;
						}
						break;
					case "chop":
						System.out.println("You've cleared your way deeper into the forest");
						System.out.println("Suddenly you find the tree stump where the dragon hides his treasures.");
						System.out.println("When you returned it to the villagers, they rewarded you with half the treasure. :)");
						break;
					default:
						System.out.println("Invalid Option...");
						break;
					}
					break;
				case "cave":
					System.out.println("The cave is locked with a big door.");
					System.out.print("What do you want to do? Open or destroy the door? (open or destroy): ");
					action1 = scan.nextLine();
					
					switch (action1.toLowerCase()) {
					case "open":
						System.out.println("The door opens smoothly and you can make your way into the cave...");
						System.out.println("You can see the treasure behind a sleeping dragon.");
						System.out.print("What do you want to do? Fight the dragon or sneak to the treasure? (fight or sneak): ");
						action2 = scan.nextLine();
						
						switch (action2.toLowerCase()) {
						case "fight":
							System.out.println("Oh nooo... your power is nothing against the dragons wrath.");
							System.out.println("Your attacks are worthless and he eats you right away... :(");
							break;
						case "sneak":
							System.out.println("You sneak very successfully around the dragon and back.");
							System.out.println("When you returned it to the villagers, they rewarded you with half the treasure. :)");
							break;
						default:
							System.out.println("Invalid Option...");
							break;
						}
						break;
					case "destroy":
						System.out.println("The noises you made while crushing the door wakes the dragon in his sleep.");
						System.out.println("After seconds he realizes what happened and burns you until there is only your ashes left on this place...");
						break;
					default:
						System.out.println("Invalid Option...");
						break;
					}
					break;
				default:
					System.out.println("Invalid Option...");
					break;
				}
				break;
			case "left":
				System.out.print("After a few miles you've found a dark path and the entry of a cove. "
						+ "Which way do you want to go (path or cove): ");
				direction2 = scan.nextLine();
				
				switch (direction2.toLowerCase()) {
				case "path":
					System.out.println("You follow the dark path.");
					System.out.println("A few very old crashed trees prevent you from following the path.");
					System.out.print("What do you want to do: burn them or chop them up with your axe? (brun or chop): ");
					action1 = scan.nextLine();
					
					switch (action1.toLowerCase()) {
					case "burn":
						System.out.println("Oh nooooo! The fire you startet burns down the entire forest...");
						System.out.println("The dragon noticed you and started the attack...");
						System.out.print("What do you want to do? Fight him or run away? (fight or run): ");
						action2 = scan.nextLine();
						
						switch (action2.toLowerCase()) {
						case "fight":
							System.out.println("You've been killed by the dragon... :(");
							break;
						case "run":
							System.out.println("You ran deeper into the burning forrest. "
									+ "Luckily, because of the smoke the dragon can't find you.");
							System.out.println("Inadvertently you find the tree stump where the dragon hides his treasures.");
							System.out.println("When you returned it to the villagers, they rewarded you with half the treasure. :)");
							break;
						default:
							System.out.println("Invalid Option...");
							break;
						}
						break;
					case "chop":
						System.out.println("You've cleared your way deeper into the forest");
						System.out.println("Suddenly you find the tree stump where the dragon hides his treasures.");
						System.out.println("When you returned it to the villagers, they rewarded you with half the treasure. :)");
						break;
					default:
						System.out.println("Invalid Option...");
						break;
					}
					break;
				case "cave":
					System.out.println("The cave is locked with a big door.");
					System.out.print("What do you want to do? Open or destroy the door? (open or destroy): ");
					action1 = scan.nextLine();
					
					switch (action1.toLowerCase()) {
					case "open":
						System.out.println("The door opens smoothly and you can make your way into the cave...");
						System.out.println("You can see the treasure behind a sleeping dragon.");
						System.out.print("What do you want to do? Fight the dragon or sneak to the treasure? (fight or sneak): ");
						action2 = scan.nextLine();
						
						switch (action2.toLowerCase()) {
						case "fight":
							System.out.println("Oh nooo... your power is nothing against the dragons wrath.");
							System.out.println("Your attacks are worthless and he eats you right away... :(");
							break;
						case "sneak":
							System.out.println("You sneak very successfully around the dragon and back.");
							System.out.println("When you returned it to the villagers, they rewarded you with half the treasure. :)");
							break;
						default:
							System.out.println("Invalid Option...");
							break;
						}
						break;
					case "destroy":
						System.out.println("The noises you made while crushing the door wakes the dragon in his sleep.");
						System.out.println("After seconds he realizes what happened and burns you until there is only your ashes left on this place...");
						break;
					default:
						System.out.println("Invalid Option...");
						break;
					}
					break;
				default:
					System.out.println("Invalid Option...");
					break;
				}
				break;
			default:
				System.out.println("Please use only the requested inputs.");
			}
		} else if (!game) {
			System.out.println("Farewell then!");
		} else {
			System.out.println("Maybe there was an error... Please try it again later.");
		}
		
		
		scan.close();
	}
}
