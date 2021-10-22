package main.application;

import java.sql.*;
import java.util.*;

public class ConnectionDatabase {
    private Connection connection;

    public ConnectionDatabase() {
        final String DB_URL = "jdbc:mysql://sql6445863:1eFfPQTeIA@sql6.freesqldatabase.com:3306/sql6445863";
        final String USER_NAME = "sql6445863";
        final String PASSWORD = "1eFfPQTeIA";

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

    public List<Words> getWordList() throws SQLException {
        try (
                Statement state = connection.createStatement();
                ResultSet res = state.executeQuery("SELECT * FROM words");
        ){
            List<Words> wordsList = new ArrayList<>();
            while (res.next()) {
                String id_w = res.getString("id_w");
                String english = res.getString("english");
                String vietnamese = res.getString("vietnamese");
                String pronounce = res.getString("pronounce");
                String parts = res.getString("parts");
                Words word = new Words(english, vietnamese, pronounce, parts);
                wordsList.add(word);
            }
            return wordsList ;
        }
    }

    public List<Words> getSearchWord(String word) throws SQLException {
        try (
                Statement state = connection.createStatement();
                ResultSet res = state.executeQuery("SELECT * FROM words WHERE english='" + word + "'");
        ){
            List<Words> wordsList = new ArrayList<>();
            while (res.next()) {
                String id_w = res.getString("id_w");
                String english = res.getString("english");
                String vietnamese = res.getString("vietnamese");
                String pronounce = res.getString("pronounce");
                String parts = res.getString("parts");
                Words searched = new Words(english, vietnamese, pronounce, parts);
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
    public void insertWord(String english, String vietnamese, String pronounce, String parts)
        throws SQLException {
        String query = "INSERT INTO words (english, vietnamese, pronounce, parts) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pState = connection.prepareStatement(query)){
                pState.setString(1, english);
                pState.setString(2,vietnamese);
                pState.setString(3,pronounce);
                pState.setString(4,parts);
                pState.executeUpdate();
        }
    }

    public void deleteWord(int id_w) throws SQLException {
        String query = "DELETE FROM words WHERE id_w=?";
        try (PreparedStatement pState = connection.prepareStatement(query)){
            pState.setInt(1, id_w);
            pState.executeUpdate();
        }
    }

    public void updateWord(String english, String vietnamese, String pronounce, String parts, int id_w)
        throws SQLException {
        String query = "UPDATE words SET english=?, vietnamese=?, pronounce=?, parts=? " +
                    "WHERE id_w=?";
        try (PreparedStatement pState = connection.prepareStatement(query)){
            pState.setString(1, english);
            pState.setString(2, vietnamese);
            pState.setString(3, pronounce);
            pState.setString(4, parts);
            pState.setInt(5, id_w);
            pState.executeUpdate();
        }
    }

    public static void main(String[] args) throws SQLException {
        ConnectionDatabase cnt = new ConnectionDatabase();
        cnt.insertWord("tals", "chuyen co tich", "taels", "noun");
        cnt.updateWord("hi", "xin chao", "hai", "verb", 7);
        for(int i=0; i<cnt.getWordList().size(); i++){
            System.out.print(cnt.getWordList().get(i).getEnglishWord() + " ");
            System.out.print(cnt.getWordList().get(i).getVietnameseWord() + " ");
            System.out.print(cnt.getWordList().get(i).getPronounceProperty() + " ");
            System.out.print(cnt.getWordList().get(i).getPartsProperty() + '\n');
        }
    }
}
