package Character;

public class Agressor extends Enemy{

    public Agressor(){
        HP = 6;
    }

    public void printCharacter(){

    }

    @Override
    public void BeAttacked(Hero hero) {
            HP -= 2;
    }


}
