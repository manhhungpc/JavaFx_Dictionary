package main.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EditWordController {

    @FXML
    public Text textTarget;
    public TextArea textFieldMeanings;
    public TextField textFieldPronounce;
    public WebView webView;
    public Button cancelButton;
    public Button applyButton;

    @FXML
    public void setApplyButton() throws SQLException {
        //Neu co textField trống thì báo lỗi
//        if (textFieldPronounce.getText().trim().isEmpty() || textFieldMeanings.getText().trim().isEmpty()) {
//            Structure.callAlert("Error", "Word target, pronounce and meanings can not be empty");
//        }

        System.out.println("edited");

//        Word oldWord = Structure.editWord;
//        String target = oldWord.getEnglishWord();
//        String pronounce = textFieldPronounce.getText();
//        String meaning = textFieldMeanings.getText();
//        Word newWord = new Word(target, pronounce, meaning);
//
//
//
//        //Edit in map
//        Structure.mapWords.remove(target);
//        Structure.mapWords.put(target, newWord);
//
//        //Edit in database
//        Structure.data.cd.deleteWord(target);
//        Structure.data.cd.insertWord(target, pronounce, meaning);
    }

    @FXML
    public void setCancelButton() throws SQLException, IOException {
        Structure.controller.setSceneSearch("");
    }

    public void edit() throws IOException {
        if (Structure.editWord.getEnglishWord().equals("NoWord")) {
            Structure.callAlert("Error", "No word is selected");
            return;
        }
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditWord.fxml"))), 650, 600);
        Structure.window.setScene(scene);
    }

}
