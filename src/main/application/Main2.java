package main.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;

import static javafx.application.Application.launch;
import static main.application.Structure.window;

public class Main2 extends Application {
    Controller controller = new Controller();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Structure.setMapWords();

        window = stage;
        window.setTitle("Stupid Dictionary");


        //set Icon for Search button
        Button searchButton = new Button();
        searchButton.setGraphic(new ImageView(Structure.search_icon));


        VBox layout = new VBox();
        layout.getChildren().add(searchButton);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);

        window.show();
    }
}
