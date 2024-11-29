package items;

public class Item {
    private final String name;

    public Item(String name){
        this.name = name;
    }

    public void printItem(){
        System.out.println(this.name);
    }
}
