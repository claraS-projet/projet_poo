package modele.Commands;

import java.util.Scanner;
import modele.Character.Hero;

public interface Command {


    static String[] read(String cmd){

        Scanner scan = new Scanner(cmd);
        String phrase = scan.nextLine();
        String[] words = phrase.split("\\s+");

        scan.close();
        System.out.println("Command Read");
        return words;
    }

    public void describe();
}
