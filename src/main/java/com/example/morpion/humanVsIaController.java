package com.example.morpion;

import ia.Config;
import ia.ConfigFileLoader;
import ia.MultiLayerPerceptron;
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
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class humanVsIaController {
    @FXML
    public TextField nom;
    public RadioButton difficile;
    public RadioButton facile;
    static Player human = new Player();
    static Player computer = new Player();
    public static String level;
    public static  String  path;
    public static int h, lh;
    public static double lr;
    public  static MultiLayerPerceptron mlp;

    String chemin = "C://Usersc//IdeaProjects//sifaoufatai//src//main//resources//com//example//morpion";

    @FXML
    public void lancerReglage(ActionEvent event) {
        Parent nouveauJeux;
        try {
            nouveauJeux = FXMLLoader.load(getClass().getResource("setting.fxml"));
            Scene configurationScene = new Scene(nouveauJeux);
            Stage stage = new Stage();
            stage.setTitle("Tic-Tac-Toe");
            stage.setScene(configurationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void lancerModeles(ActionEvent event) {
        Parent nouveauJeux;
        try {
            nouveauJeux = FXMLLoader.load(getClass().getResource("model-view.fxml"));
            Scene configurationScene = new Scene(nouveauJeux);
            Stage stage = new Stage();
            stage.setTitle("Tic-Tac-Toe");
            stage.setScene(configurationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void help(ActionEvent actionEvent) {

        JOptionPane jOption;
        jOption = new JOptionPane();
        jOption.showMessageDialog(null, "Pour jouer, vous devriez cliquer sur le menu Réglage pour regler le niveau de l'IA.\n donner le nom du joueur .\n vous devriez choisir le niveau Facile ou Difficile à jouer.\n Cliquer sur le bouton Commencer pour commencer\n ", "Aide", JOptionPane.INFORMATION_MESSAGE);
    }


    @FXML

    public void selectNiveau(ActionEvent event) {
        computer.setName("Computer ");
        if (nom.getText() == "") human.setName("Human");
        else human.setName(nom.getText());
        computer.setPawn("O");
        human.setPawn("x");
        human.setStart(true);
        computer.setStart(false);


        if (difficile.isSelected()) {
            facile.setSelected(false);
            level = "D";
            ConfigFileLoader configFileLoader = new ConfigFileLoader();
            configFileLoader.loadConfigFile("resources/config.txt");
            Config config;
            config = configFileLoader.get("D");
            h = config.hiddenLayerSize;
            lr = config.learningRate;
            lh = config.numberOfhiddenLayers;



        } else if (facile.isSelected()) {
            level = "F";
            difficile.setSelected(false);
            ConfigFileLoader configFileLoader = new ConfigFileLoader();
            configFileLoader.loadConfigFile("resources/config.txt");
            Config config;
            config = configFileLoader.get("F");
            h = config.hiddenLayerSize;
            lr = config.learningRate;
            lh = config.numberOfhiddenLayers;


        }
    }

    @FXML
    public void commencerJeux(ActionEvent event) {



            //verification dans le fichier config.txt
          Boolean r = checkModel(h , lr, lh );

            if(r) {	//le fichier existe il nous load le chemin du fichier et ouvre la fenetre Joueur vs IA
                Parent nouveauJeux;
                try {
                    System.out.println("jai un problemme ");

                     path = "C://Users//etudiant//Desktop//Morpion//resources//models//mlp_"+h+"_"+lr+"_"+lh+".srl";
                    mlp = MultiLayerPerceptron.load(path);

                    nouveauJeux = FXMLLoader.load(getClass().getResource("humanVsComputerBoard.fxml"));
                    Scene configurationScene = new Scene(nouveauJeux);
                    //cette ligne permet de recuperer les infos
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(configurationScene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(!r ) {	//le fichier n'existe pas il recupere le chemin et lance la fenetre d'apprentissage
                path = "C://Users//etudiant//Desktop//Morpion//resources//models//mlp_"+h+"_"+lr+ "_" +lh+".srl";
                Parent apprentissage;
                try {
                    apprentissage = FXMLLoader.load(getClass().getResource("Progessbar.fxml"));
                    Scene apprentissageScene = new Scene(apprentissage);
                    System.out.println(lr + human.toString());
                    //cette ligne permet de recuperer les infos
                    Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage1.setScene(apprentissageScene);
                    stage1.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    public Boolean checkModel(int h, double lr, int lh) {
        String path = "C://Users//etudiant//Desktop//Morpion//resources//models//mlp_"+h+"_"+lr+ "_" +lh+".srl";
        File file = new File(path);
        if (file.exists()) {
            return true;
        } else return false;
    }

    public void createFile(int h, double lr, int lh) throws IOException {

        try {
            path = "C://Users//etudiant//Desktop//Morpion//resources//models//mlp_"+h+"_"+lr+ "_" +lh+".srl";
           // String path = "C:\Users\etudiant\Desktop\Morpion\resources\models\mlp_" + h + "_" + lr + "_" + lh + ".srl";
            File file = new File(path);

            Boolean b;
            b = file.createNewFile();
            if (!b) {
                System.out.println("le fichier existe déja ");
            }
            if (b) System.out.println("le fichier à été créér ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
