package modele.AddedForGUI;


import modele.Character.Hero;
import modele.map.Location;
import modele.map.MyMap;

public class gameState {
    private final Hero hero;
    private final MyMap map;

    public gameState(Hero hero, MyMap map) {
        this.hero = hero;
        this.map = map;
    }

    public Hero getHero() {
        return hero;
    }

    public MyMap getMap() {
        return map;
    }

    // Exemple de méthode centralisée
    public void goTo(String locationName) {
        hero.setPosition(locationName);
    }

    public void take(String itemName) {
        hero.addItemToBackpack(itemName);
    }

    public void use(String item, String with) {
        // appelle USE sur le héros avec arguments
    }

    public void askGuide() {
        hero.getPosition().getCharacters().forEach(c -> {
            c.display_help();
            c.mission(hero);
        });
    }

    public boolean isGameOver() {
        return hero.getHP() <= 0;
    }

    public boolean isVictory() {
        return hero.getPosition().getName().equalsIgnoreCase("URANUS");
    }

    public boolean isDefeat() {
        return hero.getPosition().getName().equalsIgnoreCase("NEPTUNE");
    }

    public Location getCurrLocation() {
        return hero.getPosition();
    }
}