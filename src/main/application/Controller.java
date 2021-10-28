package main.application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static main.application.EditHtml.splitMeaning;
import static main.application.Structure.*;

public class Controller {

    @FXML
    public WebView resultField = new WebView();
    public ListView<String> wordListView = new ListView<>();
    public TextField inputSearchKey = new TextField();
    public Button searchButton = new Button();
    public Button homeButton = new Button();
    public Button speakButton;
    public Button addButton;
    public Button editButton;
    public Button deleteButton;

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
    public void search() throws IOException, SQLException {
        setSceneSearch(inputSearchKey.getText());
    }

    @FXML
    void goHomePage() throws IOException, SQLException {
        setSceneSearch("");
    }

    @FXML
    public void setSpeakButton() {
        if (Objects.equals(speakWord, "NoWord")) {
            callAlert("Error", "No word is selected");
            return;
        }
        TextToSpeech textToSpeech = new TextToSpeech(Structure.speakWord);
    }

    @FXML
    public void setAddButton() {
        Add.addWord();
    }

    @FXML
    public void setDeleteButton() throws SQLException, IOException {

        //Nếu chưa chọn từ nào thì tạo ra Alert
        if (Objects.equals(deleteWord, "NoWord")) {
            callAlert("Error", "No word is selected");
            return;
        }

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "", yes, no);
        alert.setHeaderText("Do you sure want to delete this word?");
        alert.setTitle("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            Structure.data.cd.deleteWord(Structure.deleteWord);
            mapWords.remove(deleteWord);
            setSceneSearch("");
        }
    }

    @FXML
    public void setEditButton() throws IOException {
        editWordController.edit();
//        WebEngine webEngine = editWordController.webView.getEngine();
//        File f = new File(".\\src\\main\\application\\resource\\edit.png");
//        webEngine.load(f.toURI().toString());
    }

    //search for keySearch and set scene
    void setSceneSearch(String keySearch) throws IOException, SQLException {
        window.setTitle("Stupid Dictionary");
        speakWord = "NoWord";
        deleteWord = "NoWord";
        editWord = new Word("NoWord", "", "");
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
                    String meanings = word.getMeanings();
                    String pronounce = word.getPronounceProperty();

                    //Load html dưới dạng String ra resultField để tiện chỉnh sửa
                    WebEngine webEngine = resultField.getEngine();
                    String htmlContent = EditHtml.htmlToString("src\\main\\application\\resource\\WordView.html");

                    //Tách String meanings ra List parts để tiện in
                    List<EditHtml.Part> parts = new ArrayList<>();
                    splitMeaning(meanings.replace('+', ':'),parts);

                    //Truyền các thuộc tính vào html
                    webEngine.loadContent(htmlContent.
                            replace("target", target).
                            replace("meanings", EditHtml.getFinalMeaning(parts)).
                            replace("pronounce", pronounce));

                    speakWord = target;
                    deleteWord = target;
                    editWord = word;
                }
        );
    }



    public void setWordListView(Scene scene, Search search) {
        selectWord(scene, search);

        //add word from mapWords to wordListView
        wordListView.getItems().addAll(search.getMapSearchWords().keySet());
    }

}
