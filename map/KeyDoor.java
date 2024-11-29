package map;

public class KeyDoor extends Exit {
	private int keyId;
	private Boolean isUnlocked;
	
	public KeyDoor(Location entrance, Location wayOut) {
		super(entrance, wayOut);
		this.isOpened = false;
		this.isUnlocked = false;
	}
	
	public void setKey(int key) {
		this.keyId = key;
	}

	public void unlocked(int keyId) {
		if (this.keyId == keyId) {
			isUnlocked = true;
			isOpened = true;
			System.out.println("The door is Unlocked and opened");
		}
		System.out.println("Wrong key, try again");
	}
	
	@Override
	public Boolean canBeCrossed() {
		if(isOpened == true) {
			return true;
		}
		return false;
	}
	
	@Override
	public void isCrossing() {
		System.out.println("You are crossing a key door");
	}
}
