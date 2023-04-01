package com.example.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class ComputerBordConttroller {
    public Button bouton1,bouton2,bouton3,bouton4,bouton5,bouton6,bouton7, bouton8,bouton9;
    @FXML
    private Label nom1, nom2, score1, score2, tourJoueur, pion1, pion2;
    @FXML
    private Button btnQuitter;

    private List<Label> infosJoueur1 = new ArrayList<>();
    private List<Label> infosJoueur2 = new ArrayList<>();
    private List<Button> listButtons = new ArrayList<>();

    public void quitterPartie(ActionEvent event){


    }
    public void choisirJoueur() {}
    public void gagner() {}



    @FXML
    public void reprendre(ActionEvent event){

    }
    
}
