package map;

public class CodeDoor extends Exit{
	private String code;
	private Boolean isUnlocked;
	
	public CodeDoor(Location entrance, Location wayOut) {
		super(entrance, wayOut);
		this.isOpened = false;
		this.isUnlocked = false;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void unlocked(String code) {
		if (this.code == code) {
			isUnlocked = true;
			isOpened = true;
			System.out.println("The door is Unlocked and opened");
		}else {
		System.out.println("Wrong code, try again");
		}
	}
	
	public void lock() {
		isUnlocked = false;
		isOpened = false;
	}
	
	
	@Override
	public Boolean canBeCrossed() {
		return isOpened;
	}
	
	@Override
	public void isCrossing() {
		System.out.println("You are crossing a code door");
	}
}
