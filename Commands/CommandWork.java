package Commands;

public class CommandWork {
    //******************* HELP WORK ***********************
    public static void displayHelp(){
        System.out.println("THERE ARE THE AVAILABLE COMMANDES OF THE GAME :");
        System.out.println("- GO (to a location) : Moves the Hero to the specified location if it's possible.");
        System.out.println("- HELP: Displays the available commands.");
        System.out.println("- LOOK (arguments) : Displays informations about the current location with no arguments " +
                           " and displays all the arguments than can be observed if there any.");
        System.out.println("- TAKE (argument) : Add the argument (item) to the Hero's Back_Pack.");
        System.out.println("- USE (argument 1) (argument 2): Makes the Hero use the argument 1 (with the argument 2 " +
                           "if there is one");
        System.out.println("- ASKG : used to ask for guide help");
        System.out.println("- QUIT : Used to quit the game.");
    }
}
