package modele.Character;

import modele.items.Item;
import modele.map.CodeDoor;

public class Guide extends MyCharacter{
    // THIS CHARACTER IS A FRIEND OF THE HERO,
    // HE GIVES HIM KEYS AND CODES TO OPEN THE LOCKED EXITS

    private String message;
    private Item key;

    public Guide(String name, int hp, String message) {
        super(name, hp);
        this.message = message;
    }


    public void printCharacter(){
        System.out.println(getName());
    }

    @Override
    public void describe(){
        System.out.println("He is your friend, he gives you keys and codes to open the locked exits");

    }

    @Override
    public void BeAttacked(String weapon, String arg, Hero hero){}

    public void setKey(Item key){
        this.key = key;
    }

    public Item getKey(){
        return key;
    }

    public void display_help(){
        System.out.println(this.message);
    }

    public void attack(MyCharacter hero){}

    public void mission(Hero hero){

        if (this.key != null && hero.locContainsKeyD()) {
            hero.getPosition().addItem(key.getName(), key);
            hero.addItemToBackpack(this.key.getName());
            this.setKey(null);
        }
        CodeDoor cd = (CodeDoor)hero.locCodeD();
        if (hero.locContainsCodeD() && cd != null && cd.getWayOut().getName() != hero.getGoal()) {
            hero.addCode(cd.getcode());
        }
        if(hero.getPosition().getName() == hero.getLocCodegoal()){
            hero.addCode(hero.getGoalCode());
        }

    }
    public String getmsg(){
        return this.message;
    }

}
