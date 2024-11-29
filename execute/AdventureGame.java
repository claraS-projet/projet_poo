package execute;

import map.*;

public class AdventureGame {
	private Location myLocation;
	
	public AdventureGame(Location start) {
		this.myLocation = start;
	}
	
	public static void main(String[] args) {
		
		//Create a new map
		MyMap gameMap = new MyMap("Solar System");
		
		//create a new place
		Location earth = new Location("Earth", "You are on Earth.", "Find a way to Mars.");
		Location mars = new Location("Mars", "The red planet.", "Look for Jupiter.");
		Location moon = new Location("Moon", "A quiet, lonely place.", "Escape to Venus.");
		Location neptune = new Location("Neptune", "An icy giant. Game Over!", "");
		Location saturn = new Location("Saturn", "A planet with rings.", "Find Venus.");
		Location jupiter = new Location("Jupiter", "The largest planet.", "Look for Uranus.");
		Location venus = new Location("Venus", "A hot and hostile environment.", "Find Uranus.");
		Location uranus = new Location("Uranus", "Congratulations! You win!", "");
		
		//Add the locations to a map
		gameMap.addLocation(earth);
		gameMap.addLocation(neptune);
		gameMap.addLocation(venus);
		gameMap.addLocation(mars);
		gameMap.addLocation(moon);
		gameMap.addLocation(jupiter);
		gameMap.addLocation(jupiter);
		
		//Create Exits
		Exit earthToMoon = new SimpleDoor(earth, moon);
		Exit earthToMars = new KeyDoor(earth, mars);

		Exit marsToSaturn = new KeyDoor(mars, saturn);
		Exit marsToJupiter = new SimpleDoor(mars, jupiter);

		Exit moonToEarth = new SimpleDoor(moon, earth);
		Exit moonToNeptune = new SimpleDoor(moon, neptune);
		Exit moonToVenus = new CodeDoor(moon, venus);

		Exit saturnToMars = new SimpleDoor(saturn, mars);
		Exit saturnToVenus = new KeyDoor(saturn, venus);

		Exit jupiterToSaturn = new SimpleDoor(jupiter, saturn);
		Exit jupiterToUranus = new CodeDoor(jupiter, uranus);

		Exit venusToMoon = new CodeDoor(venus, moon);
		Exit venusToSaturn = new SimpleDoor(venus, saturn);
		Exit venusToUranus = new KeyDoor(venus, uranus);
		
		//create keys
		((KeyDoor) venusToUranus).setKey(1);
		((KeyDoor) earthToMars).setKey(2);
		((KeyDoor) marsToSaturn).setKey(3);
		((KeyDoor) saturnToVenus).setKey(4);
		((CodeDoor) moonToVenus).setCode("5");
		((CodeDoor) jupiterToUranus).setCode("6");
		((CodeDoor) venusToMoon).setCode("7");
		
		//create a new exit for an existing place
		earth.addExit("Moon", earthToMoon);
		earth.addExit("Mars", earthToMars);

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
		
		//game creation
		AdventureGame game = new AdventureGame(earth);
		
		earth.describe();
		
	}

}
