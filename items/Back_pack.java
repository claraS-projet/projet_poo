package items;

import java.util.ArrayList;

public class Back_pack extends Item{
    private ArrayList<Item> item_list;

    public Back_pack(){
        super("Back_Pack");
        item_list = new ArrayList<>();
    }
    public void add_item(Item item){
        item_list.add(item);
    }

    public void remove_item(Item item){
        item_list.remove(item);
    }

    public void print_item_list(){
        for(Item item : item_list){
            printItem();
        }
    }
}
