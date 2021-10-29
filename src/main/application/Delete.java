package main.application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


//class MessageBox {
//    public static void show(String message, String title) {
//        Stage window = new Stage();
//        window.initModality(Modality.APPLICATION_MODAL);
//        window.setTitle(title);
//        window.setMinWidth(250);
//
//        Label lbl = new Label();
//        lbl.setText(message);
//
//        Button button = new Button();
//        button.setText("OK");
//        button.setOnAction(e -> window.close());
//
//        VBox pane = new VBox(20);
//        pane.getChildren().addAll(lbl, button);
//        pane.setAlignment(Pos.CENTER);
//
//        Scene scene = new Scene(pane);
//        window.setScene(scene);
//        window.showAndWait();
//    }
//}

public class Delete {

    private void deleteWord() {
        Stage window = new Stage();
//        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Xóa từ");
        window.setMinWidth(300);

        TextField inputField = new TextField();
        Label input = new Label();
        input.setText("Nhập từ");
        inputField.setPromptText("Nhập từ bạn muốn xóa");
        Label output = new Label();
        output.setText("Xóa từ thành công");
        Button confirm = new Button();
        confirm.setText("Xóa");
        confirm.setOnAction(event -> {
            String s = inputField.getText();
            // hàm xóa trong db



            //TH ko có từ trong dict
            //MessageBox.show("Lỗi!", "");
            Stage AlertBox = new Stage();
//            AlertBox.initModality(Modality.APPLICATION_MODAL);
            AlertBox.setTitle("Lỗi");
            Label alert = new Label();
            alert.setText("Không có trong từ điển");
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(confirm);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(input, inputField, confirm);
        Scene scene = new Scene(vBox, 300, 300);
        window.setScene(scene);
    }
}






