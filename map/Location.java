package map;
import java.util.*;

import items.Item;
import items.MyCharacter;

public class Location {
	private String name;
	private String description; //What you can see in this place
	private String goal;  //how to go to the next room
	private HashMap<String, Exit> exits = new HashMap<>(); //All the exits of this location are associated with their name
	private ArrayList<MyCharacter> characters;
	private ArrayList<Item> items;
	
	public Location(String name, String description, String goal) {
		this.name = name;
		this.description = description;
		this.goal = goal;
		characters = new ArrayList<>();
		items = new ArrayList<>();
	}
	
	public void addItem(Item myitem) {
		items.add(myitem);
	}
	
	public void remItem(Item myitem) {
		items.remove(myitem);
	}
	
	public void addChar(MyCharacter myChar) {
		characters.add(myChar);
	}
	
	public void remChar(MyCharacter mychar) {
		characters.remove(mychar);
	}
	
	public void addExit(String name, Exit myExit) {  //Add an exit to the Location
		exits.put(name, myExit);
	}
	
	public void remExit(String name) {  //Remove an exit to the Location
		exits.remove(name);
	}
	
	public void describe() { //print the caracteristics of the exits and of this location
		System.out.println("You are in" + name);
		System.out.println("Possible exits :");
		System.out.println(exits);
	}
	
	public Exit getExit(String name) { //returns the exit associated to the name in this location
		return exits.get(name);
	}
	
	public boolean isContainExit(String name) { //true if the location contain an exit associated with the name
		if (exits.containsKey(name))
			return true;
		return false;
	}
	
	/*public void cross(String exitName) {
		if (isContainExit(exitName) && canBecrossed(exitName))
	}*/
	
	public static void main(String[] args) {
	 Location l = new Location(null, null, null);
	 System.out.println("test");
	}
}
