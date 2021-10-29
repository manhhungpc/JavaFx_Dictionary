module JavaFxApplication {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires mysql.connector.java;
    requires freetts;
    requires jsapi;

    opens main.application;
}