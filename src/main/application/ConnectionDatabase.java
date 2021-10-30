package main.application;

import java.sql.*;
import java.util.*;

public class ConnectionDatabase {
    private Connection connection;

    public ConnectionDatabase() {
        final String DB_URL = "jdbc:mysql://sql6447574:FWykaM649T@sql6.freesqldatabase.com:3306/sql6447574";
        final String USER_NAME = "sql6447574";
        final String PASSWORD = "FWykaM649T";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void shutdown() throws SQLException{
        if (connection != null) {
            connection.close();
        }
    }

    public List<Word> getWordList() throws SQLException {
        try (
                Statement state = connection.createStatement();
                ResultSet res = state.executeQuery("SELECT * FROM list_words");
        ){
            List<Word> wordsList = new ArrayList<>();
            while (res.next()) {
                String english = res.getString("english");
                String pronounce = res.getString("pronounce");
                String meanings = res.getString("meanings");
                Word word = new Word(english, pronounce, meanings);
                wordsList.add(word);
            }
            return wordsList ;
        }
    }

    public List<Word> getSearchWord(String word) throws SQLException {
        try (
                Statement state = connection.createStatement();
                ResultSet res = state.executeQuery("SELECT * FROM list_words WHERE english LIKE '" + word + "%'");
        ){
            List<Word> wordsList = new ArrayList<>();
            while (res.next()) {
                String english = res.getString("english");
                String pronounce = res.getString("pronounce");
                String meanings = res.getString("meanings");
                Word searched = new Word(english, pronounce, meanings);
                wordsList.add(searched);
            }
            return wordsList ;
        }
    }

    /**
     * reference document
     * https://stackoverflow.com/questions/19614544/sql-question-marks-in-insert-statement/19614558
     *
     */
    public void insertWord(String english, String pronounce, String meanings)
        throws SQLException {
        String query = "INSERT INTO list_words (english, pronounce, meanings) VALUES (?, ?, ?)";
        try (PreparedStatement pState = connection.prepareStatement(query)){
                pState.setString(1, english);
                pState.setString(2,pronounce);
                pState.setString(3,meanings);
                pState.executeUpdate();
        }
    }

    public void deleteWord(String english) throws SQLException {
        String query = "DELETE FROM list_words WHERE english=?";
        try (PreparedStatement pState = connection.prepareStatement(query)){
            pState.setString(1, english);
            pState.executeUpdate();
        }
    }

    public void updateWord(String english, String pronounce, String meanings)
        throws SQLException {
        String query = "UPDATE list_words SET english=?, pronounce=?, meanings=? " +
                    "WHERE english=?";
        try (PreparedStatement pState = connection.prepareStatement(query)){
            pState.setString(1, english);
            pState.setString(2, pronounce);
            pState.setString(3, meanings);
            pState.executeUpdate();
        }
    }

    public static void main(String[] args) throws SQLException {
        ConnectionDatabase cd = new ConnectionDatabase();
//        cnt.insertWord("tals", "chuyen co tich", "taels", "noun");
//        cnt.updateWord("hi", "xin chao", "hai", "verb", 7);
//        for(int i=0; i<cnt.getWordList().size(); i++){
//            System.out.print(cnt.getWordList().get(i).getEnglishWord() + " ");
//            System.out.print(cnt.getWordList().get(i).getVietnameseWord() + " ");
//            System.out.print(cnt.getWordList().get(i).getPronounceProperty() + " ");
//            System.out.print(cnt.getWordList().get(i).getPartsProperty() + '\n');
//        }
        Map<String, Word> mp = cd.getMapWord();
        for (String i : mp.keySet()) {
            System.out.println(i + " " + mp.get(i).getMeanings());
        }
    }

    public Map<String, Word> getMapWord() throws SQLException {
        try (
                Statement state = connection.createStatement();
                //try print this one: SELECT * FROM list_words LIMIT 20
                ResultSet res = state.executeQuery("SELECT * FROM list_words");
        ){
            Map<String, Word> mapWords = new TreeMap<>();
            while (res.next()) {
                String english = res.getString("english");
                String pronounce = res.getString("pronounce");
                String meanings = res.getString("meanings");
                Word word = new Word(english, pronounce, meanings);
                mapWords.put(english, word);
            }
            return mapWords;
        }
    }
}
