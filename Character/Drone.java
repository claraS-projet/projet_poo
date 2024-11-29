package Character;

public class Drone extends Enemy{

    public Drone(){
        HP = 6;
    }

    @Override
    public void printCharacter(){

    }

    @Override
    public void BeAttacked(Hero hero) {
        if (hero.CanAttaqueDrone()){
            HP -= 3;
            hero.loseArrow();
        }
    }


}
