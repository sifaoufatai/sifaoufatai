package com.example.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class humanvshumanControler {
    public TextField pseudo1;
    public TextField pseudo2;
    public RadioButton radioJoueur1;
    public RadioButton radioJoueur2;
    public static Player player1 = new Player();
    public static Player  player2 = new Player();
    //quand on clique sur le boutton aide il nous affiche le boite du message d'information
    @FXML
    public void help(ActionEvent event) {
        JOptionPane jOption;
        jOption = new JOptionPane();
        jOption.showMessageDialog(null, "To play , you need to enter your name .\n choose  the first player .\n Click on start Button\n ", "help", JOptionPane.INFORMATION_MESSAGE);
    }


    @FXML
    public void commencerJeux(ActionEvent actionEvent) {
        System.out.println(player1.getName());
        Parent newgame;
        try {
            selectJoueur();
            newgame = FXMLLoader.load(getClass().getResource("humanvshumangameBord.fxml"));
            Scene configurationScene = new Scene(newgame);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(configurationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void selectJoueur(ActionEvent actionEvent) {
        player1.setName(pseudo1.getText());
        player2.setName(pseudo2.getText());

        player1.setPawn("O");
        player2.setPawn("X");
        if(radioJoueur1.isSelected()==true){
            player1.setStart(true);
            radioJoueur2.setSelected(false);
        } else if (radioJoueur2.isSelected()==true) {
            player2.setStart(true);
            radioJoueur1.setSelected(false);

        }

    }

    public void selectJoueur() {
        player1.setName(pseudo1.getText());
        player2.setName(pseudo2.getText());

        player1.setPawn("O");
        player2.setPawn("X");

        player1.setStart(true);






    }
    // forcer les joueur à remplir leurs pseudo
  /* public void defaultvalue(){
        if(pseudo1.getText()=="") player1.setName("player1");
        if(pseudo2.getText()=="") player1.setName("player2");
        if(!radioJoueur1.isSelected() && !radioJoueur2.isSelected()) {


            player1.setStart(true);
            player2.setStart(false);
            radioJoueur2.setSelected(false);


        }*/






}
