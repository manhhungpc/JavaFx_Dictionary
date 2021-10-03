package main.commandline;

import java.util.Collection;
import java.util.Collections;

import static main.commandline.Dictionary.words;
import static main.commandline.DictionaryManagement.dictionaryLookup;
import static main.commandline.DictionaryManagement.insertFromFile;

public class DictionaryCommandLine {

    /**
     * Print one word on Command Line.
     * @param number word's number in Dictionary.
     * @param target word's target.
     * @param explain word's explain.
     */
    public static void showWord(String number, String target, String explain) {
        System.out.printf("%1$-5s| %2$-25s" , number, target);
        System.out.println("| " + explain);
    }

    /**
     * Print all words in Dictionary.
     */
    public static void showAllWords(){
        showWord("No", "English", "Vietnamese");
        for(int i=0; i< Dictionary.getWords().size(); i++){
            showWord(Integer.toString(i+1), Dictionary.getWords().get(i).getWord_target(), Dictionary.getWords().get(i).getWord_explain());
        }
    }

    /**
     * Insert new words from Command Line.
     * Print all words on Command Line.
     */
    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        DictionaryCommandLine.showAllWords();
    }

    public static void dictionaryAdvanced() {
        insertFromFile();
        showAllWords();
        dictionaryLookup();
    }
}
