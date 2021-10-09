package main.commandline;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static main.commandline.Dictionary.words;
import static main.commandline.DictionaryCommandLine.showAllWords;
import static main.commandline.DictionaryCommandLine.showWord;

public class DictionaryManagement {

    /**
     * Insert from Command Line
     */
    public static void insertFromCommandline() {

        Scanner input = new Scanner(System.in);
        System.out.print("Number of words: ");
        int number = input.nextInt();
        input.nextLine();


        for (int i = 1; i <= number; i++) {
            System.out.println("No " + i);
            System.out.print("English: ");
            String target = input.nextLine();
            System.out.print("Vietnamese: ");
            String explain = input.nextLine();
            Dictionary.addNewWord(new Word(target, explain));
        }
    }


    /**
     * Insert words from file "dictionaries.txt".
     * Note: if you want to change data in file, please use Notepad and remember a TAB between target and explain.
     */
    public static void insertFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(".\\src\\main\\data\\dictionaries.txt"));
            String line;
            String[] targetAndExplain; // = {target, explain}

            while ((line = br.readLine()) != null) {
                targetAndExplain = line.split("\t");
                Dictionary.addNewWord(new Word(targetAndExplain[0], targetAndExplain[1]));
            }

            br.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: " + ex);
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

    public void addWord() {
        Word newWord = new Word();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the word you want to add:");
        System.out.print("Word in English: ");
        newWord.setWord_target(sc.nextLine());
        System.out.print("Explain in Vietnamese: ");
        newWord.setWord_explain(sc.nextLine());
        Dictionary.addNewWord(newWord);
        dictionaryExportToFile();
        System.out.println("Completed!");
    }

    /**
     * @throws IOException
     */
    public void deleteWord() throws IOException {
        System.out.print("Enter your word: ");
        Scanner sc = new Scanner(System.in);
        String wordDel = sc.nextLine();
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            Word word = Dictionary.getWords().get(i);
            if (word.getWord_target().equals(wordDel)) {
                Dictionary.words.remove(word);
                break;
            }
        }
    }

    public void editWord() {
        Scanner sc = new Scanner(System.in);
        boolean check = false;
            System.out.print("Enter your word: ");
            String editWord = sc.next();
            for (int i = 0; i < Dictionary.getWords().size(); i++) {
                if (editWord.equals(Dictionary.getWords().get(i).getWord_target())) {
                    System.out.print("Edit the meaning of the word: ");
                    String exWord = sc.nextLine();
                    Dictionary.getWords().get(i).setWord_explain(exWord);
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println("Word not found!"); // không tìm ra từ cần sửa
            }
    }

//    public void dictionaryExportToFile() {
//        try {
//            File f = new File(".\src\main\data\dictionaries.txt");
//            FileWriter fw = new FileWriter(f);
//
//            // Change words array to string
//            String wordsString = "";
//            for (Word word : words) {
//                wordsString += word.getWord_target() + "\t" + word.getWord_explain() + "\n";
//            }
//
//            fw.write(wordsString);      // Store data into txt file
//            fw.close();                 //close file
//        } catch (IOException ex) {
//            System.out.println("An error occurred: " + ex);
//        }
//    }

    public void dictionaryExportToFile() {
        try {
            FileWriter myWriter = new FileWriter("dictionaries.txt");
            for (int i = 0; i < Dictionary.getWords().size(); i++) {
                myWriter.write(Dictionary.getWords().get(i).getWord_target() + "    " + Dictionary.getWords().get(i).getWord_explain());
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("An error occurred!");
            ex.printStackTrace();
        }
    }
}

