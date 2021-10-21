package main.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static main.application.Structure.scene;
import static main.application.Structure.window;

public class Controller {
    Data data = new Data();

    @FXML
    public WebView resultField = new WebView();

    @FXML
    public ListView<String> wordListView = new ListView<>();

    @FXML
    public TextField inputSearchKey = new TextField();

    @FXML
    //Press Enter can use as Search button
    public void setOnEnterPress() {
        inputSearchKey.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        search();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public Button searchButton = new Button();

    @FXML
    public void search() throws IOException {
        setSceneSearch(inputSearchKey.getText());
    }

    @FXML
    public Button homeButton = new Button();

    @FXML
    void goHomePage() throws IOException {
        setSceneSearch("");
    }

    //search for keySearch and set scene
    void setSceneSearch(String keySearch) throws IOException {
        Search search = new Search(keySearch, data.getMapWords());
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml"))), 450, 400);
        setWordListView(scene, search);
        window.setScene(scene);
    }

    /**
     * add Listener for wordListView
     */
    public void selectWord(Scene scene, Search search) {
        resultField = (WebView) scene.lookup("#resultField");
        wordListView = (ListView<String>) scene.lookup("#wordListView");

        // load selected word
        wordListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    String explain = search.getMapSearchWords().get(newValue.trim());
                    resultField.getEngine().loadContent(explain, "text/html");
                }
        );
    }

    public void setWordListView(Scene scene, Search search) {
        selectWord(scene, search);

        //add word from mapWords to wordListView
        wordListView.getItems().addAll(search.getMapSearchWords().keySet());
    }

}
