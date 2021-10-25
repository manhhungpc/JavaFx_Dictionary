package main.application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;

import static main.application.Structure.window;

public class Controller {

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
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public Button searchButton = new Button();

    @FXML
    public void search() throws IOException, SQLException {
        setSceneSearch(inputSearchKey.getText());
    }

    @FXML
    public Button homeButton = new Button();

    @FXML
    void goHomePage() throws IOException, SQLException {
        setSceneSearch("");
    }

    //search for keySearch and set scene
    void setSceneSearch(String keySearch) throws IOException, SQLException {
        Search search = new Search(keySearch, Structure.mapWords);
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml"))), 650, 600);
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

                    //newValue.trim(): selected word
                    //Lấy các thuộc của từ được chọn ra String
                    Word word = search.getMapSearchWords().get(newValue.trim());
                    String target = word.getEnglishWord();
                    String explain1 = word.getVietnameseWord();
                    String part = word.getPartsProperty();
                    String pronounce = word.getPronounceProperty();

                    //Truyền các thuộc tính trên vào html và in ra Webview (resultField)
                    WebEngine webEngine = resultField.getEngine();
                    String htmlContent = EditHtml.htmlToString("src\\main\\application\\resource\\WordView.html");
                    webEngine.loadContent(htmlContent.
                            replace("target", target).
                            replace("explain1", explain1).
                            replace("pronounce", pronounce).
                            replace("part", part));
                }
        );
    }

    public void setWordListView(Scene scene, Search search) {
        selectWord(scene, search);

        //add word from mapWords to wordListView
        wordListView.getItems().addAll(search.getMapSearchWords().keySet());
    }

}
