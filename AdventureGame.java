import map.Location;

public class AdventureGame {
	private Location myLocation;
	
	public AdventureGame(Location start) {
		this.myLocation = start;
	}
	
	public static void main(String[] args) {
		
		//create a new place
		Location bu = new Location("Bu", "You are in the bu", "You want to go to the b04");
		Location b04 = new Location("B04", "You are in the b04", "Congrats you made it!");
		
		//create a new exit for an existing place
		bu.addExit("b04", new SimpleDoor(b04));
		
		//game creation
		AdventureGame game = new AdventureGame(bu);
		
		bu.describe();
		
	}

}
