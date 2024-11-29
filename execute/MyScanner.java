package execute;

import java.util.Scanner;

public class MyScanner {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String phrase = "?";
		String[] words;

		while (!phrase.equals("")) {
			phrase = scan.nextLine();
			words = phrase.split("\\s+");;
			for (String word : words) {
				System.out.println(word);
			}
		}
		scan.close();
		System.out.println("Bye");
	}

}