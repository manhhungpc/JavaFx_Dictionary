package main.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.commandline.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Controller {
    Data data = new Data();
    Stage stage = new Stage();

    void setStage (Stage stage) {
        this.stage = stage;
    }

    @FXML
    public WebView resultField = new WebView();

    @FXML
    public ListView<String> wordListView = new ListView<>();

    @FXML
    public TextField inputSearchKey = new TextField();

    @FXML
    public Button searchButton = new Button();

    @FXML
    void search() throws IOException {
        Search search = new Search(data.getMapWords());
        search.setKey(inputSearchKey.getText());
        System.out.println(search.getKey());
        System.out.println(search);
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml"))), 450, 400);
        stage.setScene(scene);
    }

}
