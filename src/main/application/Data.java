package main.application;

import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class Data {
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
