package main.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static main.application.Structure.*;

public class EditWordController {

    @FXML
    public Text textTarget = new Text("suck");
    public TextArea textFieldMeanings;
    public TextField textFieldPronounce;
    public Button cancelButton;
    public Button applyButton;
    public Button showOldWordButton;
    public TextField textFieldTarget;

    public EditWordController() {
//        textTarget.setText("suck");
//        textFieldPronounce.setText(editWord.getPronounceProperty());
//        textFieldMeanings.setText(editWord.getMeanings());
    }

    @FXML
    public void setApplyButton() throws SQLException, IOException {
        //Neu co textField trống thì báo lỗi
        if (textFieldPronounce.getText().trim().isEmpty()
                || textFieldMeanings.getText().trim().isEmpty()
                || textFieldTarget.getText().trim().isEmpty()) {
            Structure.callAlert("Error", "Word target, pronounce and meanings can not be empty");
            return;
        }

        //set a newWord
        String target = textFieldTarget.getText();
        String pronounce = textFieldPronounce.getText();
        String meanings = textFieldMeanings.getText();

        //delete \t and \n in meanings
        meanings = meanings.replaceAll("\t", "");
        meanings = meanings.replaceAll("\n", "");

        Word newWord = new Word(target, pronounce, meanings);

        //TH đổi target mà target mới đã tồn tại thì đưa ra Alert
        if (!target.equals(editWord.getEnglishWord()) && Structure.mapWords.containsKey(target)) {
            callAlert("Error", "Word is already existed!");
            return;
        }

        //Edit in map
        Structure.mapWords.remove(editWord.getEnglishWord());
        Structure.mapWords.put(target, newWord);

        //Edit in database
        Structure.data.cd.deleteWord(editWord.getEnglishWord());
        Structure.data.cd.insertWord(target, pronounce, meanings);

        //Thông báo thành công
        Structure.callAlert("Congrats!", "Editing word successfully");

        //Trở về màn hình chính
        controller.setSceneSearch("");
    }

    @FXML
    public void setCancelButton() throws SQLException, IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "", yes, no);
        alert.setHeaderText("Do you sure want to cancel editing word?");
        alert.setTitle("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            controller.setSceneSearch("");
        }
    }

    public void edit() throws IOException {
        //if select no word then Alert
        if (Structure.editWord.getEnglishWord().equals("NoWord")) {
            Structure.callAlert("Error", "No word is selected");
            return;
        }

        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditWord.fxml"))), 650, 600);
        Structure.window.setScene(scene);
    }

    public void setOldWordInTextField() {
        textFieldTarget.setText(editWord.getEnglishWord());
        textFieldPronounce.setText(editWord.getPronounceProperty());
        textFieldMeanings.setText(transferMeanings(editWord.getMeanings()));
    }

    public String transferMeanings(String meanings) {
        String ans = meanings.replaceAll("-", "\n\t-");
        ans = ans.replaceAll("=", "\n\t\t=");
        ans = ans.replaceAll(Pattern.quote("*"), "\n*");
        return ans.substring(1);
    }
}
