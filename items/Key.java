package items;

public class Key extends Item{
    //int id;
    String description;

    public Key(String name, String description) {
        super(name);
        //this.id = id;
        this.description = description;
    }
/*
    public int getId(){
        return id;
    }
*/
    @Override
    public void describe() {
        System.out.println("It's a Key to open a KeyDoor");
    }

    public void taken(){
        System.out.println("KEY TAKEN");
    }
}
