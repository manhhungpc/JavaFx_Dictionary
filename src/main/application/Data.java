package main.application;

import java.sql.SQLException;
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
    ConnectionDatabase cd = new ConnectionDatabase();
    private Map<String, Word> mapWords;

    public Data() {
        mapWords = new TreeMap<>();
//        insertFromFile();
    }

    public void setMapWordsFromDataBase() throws SQLException {
        mapWords = cd.getMapWord();
    }

    public Map<String, Word> getMapWordsFromDataBase() throws SQLException {
        setMapWordsFromDataBase();
        return mapWords;
    }

//    /**
//     * first import data from dictionaries.txt to mapWords.
//     */
//    public void insertFromFile() {
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(".\\src\\main\\data\\dictionaries.txt"));
//            String line;
//            String[] targetAndExplain; // = {target, explain}
//
//            while ((line = br.readLine()) != null) {
//                targetAndExplain = line.split("\t");
//                addWord(targetAndExplain[0], targetAndExplain[1]);
//            }
//
//            br.close();
//        } catch (Exception ex) {
//            System.out.println("Loi doc file: " + ex);
//        }
//    }

//    public void addWord(String target, String explain) {
//        mapWords.put(target, explain);
//    }
}
