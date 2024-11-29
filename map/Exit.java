package map;

public abstract class Exit {
	protected Boolean isOpened;
	private Location entrance;
	private Location wayOut;
	
	public Exit(Location entrance, Location wayOut) {
		this.entrance = entrance;
		this.wayOut = wayOut;
	}
	
	public Location getEntrance() {
		return entrance;
	}
	
	public Location getWayOut() {
		return wayOut;
	}
	
	public void close() {
		isOpened = false;
	}
	
	public abstract Boolean canBeCrossed();
	
	public abstract void isCrossing();
}
