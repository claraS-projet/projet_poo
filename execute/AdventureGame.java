package execute;

import map.*;
import Character.Hero;

public class AdventureGame {
	private Location myLocation;
	
	public AdventureGame(Location start) {
		this.myLocation = start;
	}
	
	public static void main(String[] args) {

		Hero hero = new Hero();

		//Create a new map
		MyMap gameMap = new MyMap("Solar System", hero);
		
		//create a new place
		Location earth = new Location("Earth", "You are on Earth.", "Find a way to mars");
        Location mars = new Location("Mars", "The red planet.", "Look for Jupiter");
        Location moon = new Location("Moon", "A quiet, lonely place.", "Escape to Venus");
        Location neptune = new Location("Neptune", "An icy giant. Game Over!", "Look for ...");
        Location saturn = new Location("Saturn", "A planet with rings.", "Look for ...");
        Location jupiter = new Location("Jupiter", "The largest planet.", "Find Uranus");
        Location venus = new Location("Venus", "A hot and hostile environment.", "Find Saturn");
        Location uranus = new Location("Uranus", "Congratulations! You win!", " ");
		
		//Add the locations to a map
		gameMap.addLocation(earth);
		gameMap.addLocation(neptune);
		gameMap.addLocation(venus);
		gameMap.addLocation(mars);
		gameMap.addLocation(moon);
		gameMap.addLocation(jupiter);
		gameMap.addLocation(jupiter);
		
		//Create Exits
		Exit earthToMars = new KeyDoor(earth, mars);
		Exit earthToMoon = new SimpleDoor(earth, moon);
		
		Exit marsToSaturn = new CodeDoor(mars, saturn);
		Exit marsToJupiter = new SimpleDoor(mars, jupiter);
		
		Exit moonToEarth = new SimpleDoor(moon, earth);
		Exit moonToNeptune = new SimpleDoor(moon, neptune);
		Exit moonToVenus = new KeyDoor(moon, venus);
		
		Exit saturnToMars = new SimpleDoor(saturn, mars);
		Exit saturnToVenus = new CodeDoor(saturn, venus);
		
		Exit jupiterToSaturn = new SimpleDoor(jupiter, saturn);
		
		Exit jupiterToUranus = new KeyDoor(jupiter, uranus);
		
		Exit venusToMoon = new CodeDoor(venus, moon);
		Exit venusToSaturn = new SimpleDoor(venus, saturn);
		Exit venusToUranus = new KeyDoor(venus, uranus);
		
		
		//create a new exit for an existing place
		earth.addExit("Mars", earthToMars);
		earth.addExit("Moon", earthToMoon);

		mars.addExit("Saturn", marsToSaturn);
		mars.addExit("Jupiter", marsToJupiter);

		moon.addExit("Earth", moonToEarth);
		moon.addExit("Neptune", moonToNeptune);
		moon.addExit("Venus", moonToVenus);

		saturn.addExit("Mars", saturnToMars);
		saturn.addExit("Venus", saturnToVenus);

		jupiter.addExit("Saturn", jupiterToSaturn);
		jupiter.addExit("Uranus", jupiterToUranus);

		venus.addExit("Moon", venusToMoon);
		venus.addExit("Saturn", venusToSaturn);
		venus.addExit("Uranus", venusToUranus);
		
		//Set keys and codes
		((KeyDoor) earthToMars).setKey(1);
		((KeyDoor) venusToUranus).setKey(2);
		((KeyDoor) moonToVenus).setKey(3);
		((KeyDoor) jupiterToUranus).setKey(4);
		
		((CodeDoor) marsToSaturn).setCode("5");
		((CodeDoor) saturnToVenus).setCode("6");
		((CodeDoor) venusToMoon).setCode("7");
		
		//game creation
		AdventureGame game = new AdventureGame(earth);
		earth.describe();
		
	}

}
