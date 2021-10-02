package main.Command_line;

import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {

    public static void insertFromCommandline(){
        Scanner input = new Scanner(System.in);
        System.out.print("Number of words: ");
        int number = input.nextInt();
        input.nextLine();

        for(int i=1; i<=number; i++) {
            System.out.println("No "+i);
            System.out.print("English: ");
            String target = input.nextLine();
            System.out.print("Vietnamese: ");
            String explain = input.nextLine();
            Dictionary.addNewWord(new Word(target, explain));
        }
    }
}
