package map;
import java.util.*;
import Character.Hero;

public class MyMap {
	private final String worldName;
	private ArrayList<Location> world; //All the location of this world
	private final Hero hero;

	//Default constructor
	public MyMap(String name, Hero hero) {
		this.world = new ArrayList<>();
		this.worldName = name;
		this.hero = hero;
	}
	
	public void addLocation(Location place) {
		world.add(place);
	}


}
