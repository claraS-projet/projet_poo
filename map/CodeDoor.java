package map;

public class CodeDoor extends Exit {
	private int code;
	
	public void open (int code) {
		if(this.code == code) {
			isOpened = true;
		}
	}
}
