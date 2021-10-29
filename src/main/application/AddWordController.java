package main.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

import static main.application.Structure.*;

public class AddWordController {

    @FXML
    public TextField targetField;
    public TextArea meaningsField;
    public TextField pronounceField;
    public Button addButton;
    public Button cancelButton;

    public void setAddButton() {
        //Neu co textField trống thì báo lỗi
        if (targetField.getText().trim().isEmpty()
                || meaningsField.getText().trim().isEmpty()
                || pronounceField.getText().trim().isEmpty()) {
            Structure.callAlert("Error", "Word target, pronounce and meanings can not be empty");
            return;
        }

        String target = targetField.getText();
        String pronounce = pronounceField.getText();
        String meanings = meaningsField.getText();

        //delete \t and \n in meanings
        meanings = meanings.replaceAll("\t", "");
        meanings = meanings.replaceAll("\n", "");


        if (Structure.mapWords.containsKey(target)) {
            //TH từ đã tồn tại thì đưa ra Alert
            callAlert("Error", "Word is already existed!");
        } else {

            //them vao database
            try {
                Structure.data.cd.insertWord(target, pronounce, meanings);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //them vao map
            mapWords.put(target, new Word(target, pronounce, meanings));

            //MessageBox.show("Thêm từ thành công", "");
            Structure.callAlert("Congrats!", "Adding word successfully");

            //set scene search
            try {
                Structure.controller.setSceneSearch("");
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCancelButton() throws SQLException, IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "", yes, no);
        alert.setHeaderText("Do you sure want to cancel adding word?");
        alert.setTitle("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            controller.setSceneSearch("");
        }
    }

    public void add() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddWord.fxml"))), 650, 600);
        window.setScene(scene);
    }
}
