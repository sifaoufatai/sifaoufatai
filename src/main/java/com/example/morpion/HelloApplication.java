package com.example.morpion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Acueil.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.setRoot(root); // Définit la racine de votre fichie

        Scene scene = new Scene(root, 930, 690);
        stage.setTitle("Hello welecom to tictac to application !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}