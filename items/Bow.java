package items;

public class Bow extends Item {
    private int Arrows = 10;
    private int BurningArrows = 2;

    public Bow() {
        super("Bow");
    }

    public void add_arrows(int a){
        Arrows += a;
    }

    public void remove_arrows(){
        Arrows -= 1;
    }

    public void add_burning_arrows(int b){
        BurningArrows += b;
    }
    public void remove_burning_arrows(){
        BurningArrows -= 1;
    }

    public int getNbArrows(){
        return Arrows;
    }
    public int getNbBurningArrows(){
        return BurningArrows;
    }
}
