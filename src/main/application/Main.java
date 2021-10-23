package main.application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.util.*;

import static main.application.Structure.*;

public class Main extends Application {
    Controller controller = new Controller();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Structure.setMapWords();

        window = stage;
        window.setTitle("Stupid Dictionary");
        scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml"))), 450, 400);
        window.setScene(scene);

        //first, show full words => search with keySearch = "";
        controller.setSceneSearch("");

        window.show();
    }

    public void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
