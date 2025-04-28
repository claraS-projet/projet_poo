package modele.Character;

import modele.items.*;
import modele.map.*;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hero extends MyCharacter{


    private Bow H_bow;
    private Sabre H_sabre;
    private Back_pack backpack;
    private Location H_position;
    private ArrayList<String> Codes;
    private String goal;
    private String locCodegoal;
    private String goalCode;


    @Override
    public void describe() {
        System.out.println("Your character is the hero, he had a bow and a sabre and back_pack full of items." +
                " your health points : " + this.getHP());
    }

    public Hero(String name, int hp, Location loc, String goal, String locgoalcode, String cd){
        super(name, hp);
        H_bow = new Bow();
        H_sabre = new Sabre();
        backpack = new Back_pack();
        H_position = loc;
        this.goal = goal;
        this.locCodegoal = locgoalcode;
        Codes = new ArrayList<>();
        goalCode = cd;
    }

    @Override
    public void printCharacter() {
        System.out.println(getName());
    }


    public boolean CanAttaqueWithAr(){
        return (H_bow.getNbArrows() > 0);
    }
    public boolean CanAttaqueWithBAr(){
        return (H_bow.getNbBurningArrows() > 0);
    }



    public void loseArrow(){
        H_bow.remove_arrows();
    }
    public void loseBurningArrows(){H_bow.remove_burning_arrows();}



    public void setPosition(String position){

        if (H_position.isContainExit(position) && H_position.getExit(position).hero_unlock(this)){
            H_position = H_position.getExit(position).getneighbor();
        }
        else{
            if (!H_position.isContainExit(position)) {
                System.out.println("No exit to this location, check your entry");
            }
        }
    }



    public void addItemToBackpack(String name){
        if (H_position.isContainsItem(name)){
            backpack.add_item(H_position.getItem(name));
            H_position.getItem(name).taken();
            H_position.remItem(name);

        }
        else{System.out.println("ITEM NOT FOUND");}
    }

    @Override
    public void BeAttacked(String weapon, String arg, Hero hero) {
    }

    public Location getPosition(){
        return H_position;
    }

    public Back_pack getBackpack() {
        return backpack;
    }
    public Bow getH_bow(){
        return H_bow;
    }

    public Sabre getH_sabre(){
        return H_sabre;
    }

    public void display_help(){}

    public void attack(MyCharacter hero){}

    public void getattacked(){
        ArrayList<MyCharacter> enemies = this.getPosition().getCharacters();
        for(MyCharacter enemy : enemies){

            enemy.attack(this);
        }
    }

    public void mission(Hero hero){}
    public ArrayList<String> getCodes(){
        return Codes;
    }

    public void addCode(String code){
        this.Codes.add(code);
    }
    /*public void setCurrLocCode(String code){
        this.Codes = code;
    }

    public void setwinCode(String code){
        this.winCode = code;
    }*/

    public boolean locContainsKeyD(){
        boolean found = false;
        HashMap<String, Exit> exits = this.H_position.getExits();
        for(Map.Entry<String, Exit> exit : exits.entrySet()){
            Exit ext = exit.getValue();
            if (ext instanceof KeyDoor){
                found = true;
            }
        }
        return found;
    }
    public boolean locContainsCodeD(){
        boolean found = false;
        HashMap<String, Exit> exits = this.H_position.getExits();
        for(Map.Entry<String, Exit> exit : exits.entrySet()){
            Exit ext = exit.getValue();
            if (ext instanceof CodeDoor){
                found = true;
            }
        }
        return found;
    }

    public Exit locCodeD(){
        HashMap<String, Exit> exits = this.H_position.getExits();
        for(Map.Entry<String, Exit> exit : exits.entrySet()){
            Exit ext = exit.getValue();
            if (ext instanceof CodeDoor){
                return ext;
            }
        }
        System.out.println("No exit found");
        return null;
    }

    public String getGoal(){
        return goal;
    }

    public String getLocCodegoal(){
        return locCodegoal;
    }
    public String getGoalCode(){
        return this.goalCode;
    }
}
