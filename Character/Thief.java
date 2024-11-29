package Character;

public class Thief extends Enemy{

    public Thief(){
        HP = 4;
    }

    public void printCharacter() {
        System.out.println(this.name);
    }

    @Override
    public void BeAttacked(Hero hero) {
        hero.addHP(this.HP);
    }
}
