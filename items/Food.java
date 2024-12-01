package items;

public class Food extends Item {
    public Food(String name){
        super(name);
    }

    public void describe() {
        System.out.println("-It is food that you can use to increase your health points");
    }

    public void taken(){
        System.out.println("FOOD TAKEN");
    }

}
