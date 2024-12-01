package Commands;


import java.util.ArrayList;
import java.util.Scanner;
import Character.Hero;
import map.Location;
import Character.MyCharacter;

import static Commands.CommandWork.displayHelp;

public class MyCommands implements Command{

    public void describe(){
        displayHelp();
    }

    public void gameLoop(Hero hero) {

        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            if (hero.getHP() <= 0){
                System.out.println("THE HERO IS DEAD ! YOU LOOSE !");
                System.exit(0);
            }
            hero.getPosition().remove_dead_chars();
            System.out.println("You're in " + hero.getPosition().getName());
            hero.getattacked();
            hero.getPosition().print_attackable_targets();
            System.out.println("Type a command : ");
            String command = scanner.nextLine();
            commandManager(command, hero);
            if (hero.getPosition().getName().equalsIgnoreCase("URANUS")) {
                stop = true;
            }else{
                if (hero.getPosition().getName().equalsIgnoreCase("NEPTUNE")){
                    stop = true;
                    System.out.println("YOU LOOSE ! NO POSSIBLE EXIT FROM NEPTUNE");
                    System.exit(0);
                }
            }
        }
        System.out.println("YOU WON !");
        System.out.println("Thank you for playing!");
    }


    public void commandManager(String command, Hero hero) {
        Scanner scanner = new Scanner(command);
        if (scanner.hasNext()) {
            String fst = scanner.next().toUpperCase();

            switch (fst) {
                case "GO":
                    GO(command, hero);
                    break;
                case "HELP":
                    HELP(command, hero);
                    break;
                case "QUIT":
                    QUIT(command, hero);
                case "USE":
                    USE(command, hero);
                    break;
                case "TAKE":
                    TAKE(command, hero);
                    break;
                case "LOOK":
                    LOOK(command, hero);
                    break;
                case "ASKG":
                    ASKG(command, hero);
                    break;
                    default:
                        System.out.println("Unknown command");
            }
        }
        else{
            System.out.println("No command Read, you should type something");
        }
    }

    public void GO(String command, Hero hero) {
        String[] phrase = Command.read(command);
        if (phrase.length == 2){
             String toUC = phrase[1].toUpperCase();
             hero.setPosition(toUC);
        }
        else{
            System.out.println("GO : This command takes only one argument");
        }

    }

    public void LOOK(String command, Hero hero) {
        String[] phrase = Command.read(command);
        if (phrase.length == 1){
            hero.getPosition().describe();
        }
        else{
            for(int i = 1; i < phrase.length; i++) {
                switch (phrase[i].toUpperCase()) {
                    case "EXITS":
                        hero.getPosition().printExits();
                        break;
                    case "ITEMS":
                        hero.getPosition().printItems();
                        break;
                    case "BACKPACK":
                        hero.getBackpack().describe();
                        break;
                    case "BOW":
                        hero.getH_bow().describe();
                        break;
                    case "SABRE":
                        hero.getH_sabre().describe();
                        break;
                    case "CHARACTERS":
                        hero.getPosition().printCharacters();
                        break;
                    case "HERO":
                        hero.describe();
                        break;
                    default:
                        System.out.println("Unknown argument for LOOK");
                        break;
                }
            }
        }
    }

    public void TAKE(String command, Hero hero) {
        String[] phrase = Command.read(command);
        if (phrase.length == 2){
            String toUC = phrase[1].toUpperCase();
            hero.addItemToBackpack(toUC);
        }
        else{
            System.out.println("TAKE : This command takes 1 argument");
        }
    }

    public void QUIT(String command, Hero hero) {
        String[] phrase = Command.read(command);
        if (phrase.length == 1){
            System.out.println("SEE YOU SOON");
            System.exit(0);
        }
        else{
            System.out.println("This command don't takes arguments");
        }
    }

    public void USE(String command, Hero hero) {
        String[] phrase = Command.read(command);
        if (phrase.length == 2){
            String toUC = phrase[1].toUpperCase();
            switch (toUC){
                case "SABRE":
                    ArrayList<MyCharacter> targets = hero.getPosition().getCharacters();
                    for(MyCharacter target : targets){
                        target.BeAttacked(toUC, null, hero);
                    }
                    break;
                case "FOOD":
                    if (hero.getBackpack().contains_item(toUC)){
                        hero.getBackpack().remove_item(toUC);
                        hero.increaseHP(2);
                        System.out.println("You've used one food of your backpack");
                        break;
                    }
                    else{
                        System.out.println("No food in your backpack");
                        break;
                    }
                default:
                    System.out.println("USE : Unknown or insufficient arguments for USE");
            }
        }else {
            if (phrase.length == 3) {
                String toUC = phrase[1].toUpperCase();
                switch (toUC) {
                    case "BOW":
                        ArrayList<MyCharacter> targets = hero.getPosition().getCharacters();
                        String toUC2 = phrase[2].toUpperCase();
                        for (MyCharacter target : targets) {
                            if (toUC2.equals("ARROW") || toUC2.equals("BURNINGARROW")) {
                                target.BeAttacked(toUC, toUC2, hero);
                            }
                            else {
                                System.out.println("unkown second argument for the BOW");
                            }

                        }
                        break;
                    default:
                        System.out.println("USE : you can only use the bow with an argument");
                }
            } else {
                System.out.println("USE : this command is used with one or two arguments only");
            }
        }

    }

    public void HELP(String command, Hero hero) {
        String[] phrase = Command.read(command);
        if (phrase.length == 1){
            displayHelp();
        }
        else{
            System.out.println("This command don't takes arguments");
        }
    }

    public void ASKG(String command, Hero hero) {
        String[] phrase = Command.read(command);
        if (phrase.length == 1){
            ArrayList<MyCharacter> chars = hero.getPosition().getCharacters();
            for(MyCharacter theCharacter : chars) {
                theCharacter.display_help();
                theCharacter.mission(hero);
            }
        }
        else{
            System.out.println("This command don't takes arguments");
        }
    }



}
