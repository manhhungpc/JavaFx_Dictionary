package main.application;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    public static Image window_icon;
    public static String speakWord = "NoWord";
    public static String deleteWord = "NoWord";
    public static Word editWord = new Word("NoWord", "", "");
    public static Controller controller = new Controller();
    public static AddWordController addWordController= new AddWordController();
    public static EditWordController editWordController = new EditWordController();

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

    public static void callAlert(String title, String header) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle(title);
        alert1.setHeaderText(header);
//        alert1.setContentText(content);
        alert1.show();
    }

    public static void setDefault() {
        window.setTitle("Stupid Dictionary");
        speakWord = "NoWord";
        deleteWord = "NoWord";
        editWord = new Word("NoWord", "", "");
    }
}
