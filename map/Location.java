package map;
import java.util.*;
import Character.MyCharacter;
import items.Item;
import java.util.HashMap;

public class Location {
	private final String name;
	private final String description; //What you can see in this place
	private final String goal;  //how to go to the next room
	private final HashMap<String, Exit> exits = new HashMap<>(); //All the exits of this location are associated with their name
	private final ArrayList<MyCharacter> characters;
	private final HashMap<String, Item> items;
	
	public Location(String name, String description, String goal) {
		this.name = name;
		this.description = description;
		this.goal = goal;
		characters = new ArrayList<>();
		items = new HashMap<>();
	}

	public void addItem(String name, Item myitem) {
		items.put(name, myitem);
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
		System.out.println(name + " : " + description);
		System.out.println("Possible exits :");
		printExits();
	}


	public void printExits() {
		for (Map.Entry<String, Exit> entry : exits.entrySet()) {
			Exit exit = entry.getValue();
			String loc = entry.getKey();
			System.out.println("The door " + exit + "leads to " + loc);
		}
	}

	public String getName(){
		return name;
	}


	public Exit getExit(String name) { //returns the exit associated to the name in this location
		return exits.get(name);
	}
	
	public boolean isContainExit(String name) { //true if the location contain an exit associated with the name
        return exits.containsKey(name);
    }

	public Item getItem(String name) { //returns the item associated to the name in this location
		return items.get(name);
	}

	public boolean isContainItem(String name) { //true if the location contain an item associated with the name
        return items.containsKey(name);
    }


}
