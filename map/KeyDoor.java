package map;

public class KeyDoor extends Exit {
	private int keyId;
	
	public KeyDoor(Location neighbor) {
		super(neighbor);
	}
	
	public void setKey(int key) {
		this.keyId = key;
	}

	public void open (int keyId) {
		if(this.keyId == keyId) {
			isOpened = true;
		}
	}
	public Boolean canBeCrossed() {
		if(isOpened = true) {
			return true;
		}
		return false;
	}
	
	@Override
	public void isCrossing() {
		System.out.println("You are crossing a key door");
	}
}
