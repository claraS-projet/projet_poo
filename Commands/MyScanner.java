package Commands;

import java.util.Scanner;

public class MyScanner {

	public static String[] read(String cmd){

		Scanner scan = new Scanner(cmd);
		String phrase = scan.nextLine();
		String[] words = phrase.split("\\s+");

		scan.close();
		System.out.println("Command Read");
		return words;
	}

}
