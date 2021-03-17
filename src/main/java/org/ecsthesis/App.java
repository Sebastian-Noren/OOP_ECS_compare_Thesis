package org.ecsthesis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("primary.fxml"));
        stage.setTitle("OOP - Thesis");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}