package main.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.commandline.Dictionary;
import main.commandline.DictionaryManagement;
import main.commandline.Word;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main extends Application {
    Data data = new Data();
    Controller controller = new Controller();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Stupid Dictionary");
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml"))), 450, 400);
        stage.setScene(scene);

        setWordListView(scene);

        stage.show();
    }

    /**
     * set first Word List View
     */
    public void setWordListView(Scene scene) {
        selectWord(scene);

        //add word from mapWords to wordListView
        controller.wordListView.getItems().addAll(data.getMapWords().keySet());

        disableWarning();
    }

    /**
     * add Listener for wordListView
     */
    public void selectWord(Scene scene) {
        controller.resultField = (WebView) scene.lookup("#resultField");
        controller.wordListView = (ListView<String>) scene.lookup("#wordListView");

        // load selected word
        controller.wordListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    String explain = data.getMapWords().get(newValue.trim());
                    controller.resultField.getEngine().loadContent(explain, "text/html");
                }
        );
    }

    public void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
