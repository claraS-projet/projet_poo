package Commands;


import java.util.Scanner;
import Character.Hero;
import map.Location;

public class MyCommands implements Command{



    public void gameLoop(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            System.out.println("Type a command : ");
            String command = scanner.nextLine();
            commandManager(command, hero);
            if (hero.getPosition().getName().equalsIgnoreCase("URANUS")) {
                stop = true;
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
            System.out.println("GO : This command takes one argument");
        }

    }

    public void LOOK(String command, Hero hero) {
        String[] phrase = Command.read(command);
        if (phrase.length == 1){
            hero.getPosition().describe();
        }
        else{
            for(int i = 1; i < phrase.length; i++) {
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

    }

    public void HELP(String command, Hero hero) {
        String[] phrase = Command.read(command);
        if (phrase.length == 1){
            CommandWork.displayHelp();
        }
        else{
            System.out.println("This command don't takes arguments");
        }
    }
}
