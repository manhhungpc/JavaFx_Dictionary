package main.application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static main.application.Structure.*;

public class Main extends Application {
    Controller controller = Structure.controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //set map words from database
        Structure.setMapWords();

        window = stage;
//        window.setTitle("Stupid Dictionary");

        //set window icon
        window.getIcons().add(window_icon);

        //first, show full words => search with keySearch = "";
        controller.setSceneSearch("");

        window.show();
    }

    public void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
