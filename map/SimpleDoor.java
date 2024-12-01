package map;

import Character.Hero;

public class SimpleDoor extends Exit {
	
	public SimpleDoor(Location entrance, Location wayOut, String name) {

		super(entrance, wayOut, name);
	}

	@Override
	public boolean hero_unlock(Hero hero) { return true; }

	@Override
	public Boolean canBeCrossed() {
		return true; //Is always open
	}
	
	@Override
	public void isCrossing() {
		System.out.println("You are crossing a simple door");
	}
}
