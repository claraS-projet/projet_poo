package items;

import Commands.Command;

public abstract class Item implements Command {
    private final String name;

    public Item(String name){
        this.name = name;
    }

    public void printItem(){
        System.out.println(this.name);
    }

    public String getName(){
        return name;
    }

    public abstract void taken();

}
