package Character;

import items.Back_pack;
import items.Item;

import java.util.ArrayList;

public abstract class Enemy extends MyCharacter{
    protected int HP;


    public abstract void printCharacter();

    public abstract void BeAttacked(Hero hero);
}
