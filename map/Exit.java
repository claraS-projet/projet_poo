package map;

public abstract class Exit {
	protected Boolean isOpened;
	private Location neighbor;
	
	public Exit(Location neighbor) {
		this.neighbor = neighbor;
	}
	
	public Location getneighbor() {
		return neighbor;
	}
	
	public void close() {
		isOpened = false;
	}
	
	public abstract Boolean canBeCrossed();
	
	public abstract void isCrossing();
}
