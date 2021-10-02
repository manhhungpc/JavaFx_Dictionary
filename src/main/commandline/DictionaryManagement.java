package main.commandline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import static main.commandline.DictionaryCommandLine.showAllWords;
import static main.commandline.DictionaryCommandLine.showWord;

public class DictionaryManagement {

    /**
     * Insert from Command Line
     */
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


    /**
     * Insert words from file "dictionaries.txt".
     * You must change filePath by the path of "dictionaries.txt" in your device.
     * Note: if you want to change data in file, please use Notepad and remember a TAB between target and explain.
     */
    public static void insertFromFile() {
        try {
            String filePath = "D:\\Java Project\\JavaFx_Dictionary\\src\\main\\data\\dictionaries.txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            String[] targetAndExplain; // = {target, explain}

            while ((line = br.readLine()) != null){
                targetAndExplain = line.split("\t");
                Dictionary.addNewWord(new Word(targetAndExplain[0], targetAndExplain[1]));
            }

            br.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: "+ex);
        }
    }

    /**
     * Finding a word in Dictionary.
     * If word is not found, print "Word not found!".
     */
    public static void dictionaryLookup() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter searching word: ");
        String searchingWord = input.nextLine();
        boolean existWord = false;
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            Word word = Dictionary.getWords().get(i);
            if (searchingWord.equals(word.getWord_target())) {
                existWord = true;
                showWord("No", "English", "Vietnamese");
                showWord(Integer.toString(i + 1), word.getWord_target(), word.getWord_explain());
                break;
            }
        }
        if (!existWord) {
            System.out.println("Word not found!");
        }

    }
}
