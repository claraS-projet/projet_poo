package Character;

import items.Back_pack;
import items.Bow;
import items.Sabre;
import map.Location;

public class Hero extends MyCharacter{

    private final String name = "Hero";
    private int HP;
    private Bow H_bow;
    private Sabre H_sabre;
    private Back_pack backpack;
    private Location H_position;

    public Hero(){
        HP = 20;
        H_bow = new Bow();
        H_sabre = new Sabre();
        backpack = new Back_pack();
        //H_position = location;
    }

    @Override
    public void printCharacter() {
        System.out.println("I'm the Hero of the game");
    }


    public boolean CanAttaqueDrone(){
        return (H_bow.getNbArrows() > 0);
    }



    public void loseArrow(){
        H_bow.remove_arrows();
    }

    public void addHP(int hp){
        HP += hp;
    }

    public void setPosition(String position){
        if (H_position.isContainExit(position)){
            H_position = H_position.getExit(position).getneighbor();
        }
        else{System.out.println("You can't access this location from here");}
    }

    public Location getPosition(){
        return H_position;
    }

    public void addItemToBackpack(String name){
        if (H_position.isContainItem(name)){
            backpack.add_item(H_position.getItem(name));
        }
        else{System.out.println("ITEM NOT FOUND");}
    }

}
