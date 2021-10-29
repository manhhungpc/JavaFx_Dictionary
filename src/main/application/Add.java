package main.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static main.application.Structure.*;


public class Add {
    public static void addWord() {
//        window.initModality(Modality.APPLICATION_MODAL);

        Label inputWordLabel = new Label();
        inputWordLabel.setText("Nhập từ");

        TextField inputWord = new TextField();
        inputWord.setPromptText("Nhập từ bạn muốn thêm");

        Label phonetic = new Label();
        phonetic.setText("Phiên âm");

        TextField phoneticInput = new TextField();
        phoneticInput.setPromptText("Nhập phiên âm của từ");

        Label detailLabel = new Label();
        detailLabel.setText("Chi tiết");

        TextArea wordDetail = new TextArea();

        Button addConfirm = new Button();
        addConfirm.setText("Thêm");

        VBox addPane = new VBox();
        addPane.getChildren().addAll(inputWordLabel, inputWord, phonetic, phoneticInput, detailLabel, wordDetail, addConfirm);

        addConfirm.setOnAction(event -> {
            String target = inputWord.getText();
            String pronounce = phoneticInput.getText();
            String meanings = wordDetail.getText();

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


        });
        Scene scene = new Scene(addPane, 650, 600);
        window.setScene(scene);
        window.setTitle("Thêm từ");
    }


}
