module JavaFxApplication {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens main.application;
}