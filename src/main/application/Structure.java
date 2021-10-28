package main.application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Map;

public class Structure {
    public static Stage window = new Stage();
    public static Data data;
    public static Map<String, Word> mapWords;
    public static Image search_icon;
    public static Image window_icon;

    static {
        try {
            search_icon = new Image(new FileInputStream(".\\src\\main\\application\\resource\\search_icon.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            window_icon = new Image(new FileInputStream(".\\src\\main\\application\\resource\\window_icon.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setMapWords() throws SQLException {
        data = new Data();
        mapWords = data.getMapWordsFromDataBase();
    }
}
