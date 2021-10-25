package main.application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Map;

public class Structure {
    public static Stage window = new Stage();
    public static Scene scene;
    public static Data data;
    public static Map<String, Word> mapWords;

    public static void setMapWords() throws SQLException {
        data = new Data();
        mapWords = data.getMapWordsFromDataBase();
    }
}
