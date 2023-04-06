package com.example.morpion;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;



public class ModelController implements Initializable{


    @FXML

    public ListView list;

    @FXML
    private TextField mod;

    @FXML
    private Button btnQuit;

    public ModelController() {
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        //pour les modeles deja appris dans la ListeView
        File repertoire = new File("C:\\Users\\pc\\IdeaProjects\\sifaoufatai\\src\\main\\resources\\com\\example\\morpion\\models");
        String liste[] = repertoire.list();
        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                list.getItems().add(liste[i]);
            }
        }
    }
    //lorsque l'on clique sur le bouton supprimer il nous supprime un modele
    @FXML
    public void delete(ActionEvent event) {
        String chemin = "C:\\Users\\pc\\IdeaProjects\\sifaoufatai\\src\\main\\resources\\com\\example\\morpion\\models\\";
        String path = chemin+list.getSelectionModel().getSelectedItem().toString();
        File file = new File(path);
        file.delete();
        list.getItems().remove(list.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void exit(ActionEvent event){
           Stage stage = (Stage) btnQuit.getScene().getWindow();
           stage.close();
    }
}


