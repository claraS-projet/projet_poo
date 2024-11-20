package map;
import java.util.*;

public class MyMap {
	private String worldName;
	private ArrayList<Location> world; //All the location of this world
	
	//Default constructor
	public MyMap(String name) {
		this.world = new ArrayList<>();
		this.worldName = name;
	}


}
