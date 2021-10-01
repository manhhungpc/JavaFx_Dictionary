package main.Command_line;

import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dict = new Dictionary();
    List<Word> words = dict.getWords();

    Scanner input = new Scanner(System.in);

    public void showAllWords(){
        System.out.println("No  | English       | Vietnamese");
        for(int i=0; i<words.size(); i++){
            System.out.print(i + "   | " + words.get(i).getWord_target());
            System.out.println("         | "+ words.get(i).getWord_explain());
        }
    }

    public void insertFromCommandline(){
        System.out.print("Number of words: ");
        int number = input.nextInt();
        input.nextLine();

        for(int i=1; i<=number; i++) {
            Word newWord = new Word();
            System.out.println("No"+i);
            System.out.print("English: ");
            newWord.setWord_target(input.nextLine());

            System.out.print("Vietnamese: ");
            newWord.setWord_explain(input.nextLine());
            words.add(newWord);
        }
    }
}
