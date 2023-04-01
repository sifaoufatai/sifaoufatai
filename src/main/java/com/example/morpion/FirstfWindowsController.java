package com.example.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstfWindowsController{
    public void OpenOptionIA(ActionEvent actionEvent) {

        Parent option;
        try {
            option = FXMLLoader.load(getClass().getResource("humanVsIa.fxml"));
            Scene scene1 = new Scene(option);
            Stage stage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage1.setTitle("Tic-Tac-Toe");

            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void OpenOption(ActionEvent actionEvent) {
        Parent optionIA;
        try {
            optionIA = FXMLLoader.load(getClass().getResource("humanVshuman.fxml"));
            Scene scene1 = new Scene(optionIA);
            Stage stage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage1.setTitle("Tic-Tac-Toe");
            stage1.setScene(scene1);
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    }


