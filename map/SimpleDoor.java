package map;

public class SimpleDoor extends Exit {
	
	public SimpleDoor(Location neighbor) {
		super(neighbor);
	}
	
	@Override
	public Boolean canBeCrossed() {
		return true; //Is always open
	}
	
	@Override
	public void isCrossing() {
		System.out.println("You are crossing a simple door");
	}
}
