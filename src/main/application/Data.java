package main.application;

import javafx.scene.control.ListView;
import javafx.scene.web.WebView;
import main.commandline.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class Data {
//    private Map<String, Word> words = new TreeMap<>();
//    private String currentSelectedWord;
//    private static Data instance = null;
//
//    public static Data getInstance() {
//        if (instance == null) {
//            instance = new Data();
//        }
//        return instance;
//    }
//
//    public String getCurrentSelectedWord() {
//        return currentSelectedWord;
//    }
//
//    public void setCurrentSelectedWord(String currentSelectedWord) {
//        this.currentSelectedWord = currentSelectedWord;
//    }

    private Map<String, String> mapWords = new TreeMap<>();

    public Data() {
        insertFromFile();
    }

    public Map<String, String> getMapWords() {
        return mapWords;
    }

    /**
     * first import data from dictionaries.txt to mapWords.
     */
    public void insertFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(".\\src\\main\\data\\dictionaries.txt"));
            String line;
            String[] targetAndExplain; // = {target, explain}

            while ((line = br.readLine()) != null) {
                targetAndExplain = line.split("\t");
                addWord(targetAndExplain[0], targetAndExplain[1]);
            }

            br.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: " + ex);
        }
    }



    public void addWord(String target, String explain) {
        mapWords.put(target, explain);
    }
}
