package com.example.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ComputerBordConttroller implements Initializable {
    public Button bouton1,bouton2,bouton3,bouton4,bouton5,bouton6,bouton7, bouton8,bouton9;
    @FXML
    private Label nom1, nom2, score1, score2, tourJoueur, pion1, pion2;
    @FXML
    private Button btnQuitter;
    static String Pawn ="";
    public int cpt1 = 0, cpt2 = 0;
    Boolean fin = false ;
    static ia.MultiLayerPerceptron net ;
    static double inputs[], outputs[];

    private List<Label> infojour1 = new ArrayList<>();
    private List<Label> infoIa = new ArrayList<>();
    private List<Button> listButtons = new ArrayList<>();
    static Player human =humanVsIaController.human;
    static Player ia =humanVsIaController.computer;
    static  String path =humanVsIaController.path;




        public void quitterPartie(ActionEvent event) {
            JOptionPane jof;
            jof = new JOptionPane();
            Frame frame;
            frame = new Frame("exit");
            if (jof.showConfirmDialog(frame, "Voulez-vous quitter la partie?", "Information!", jof.YES_NO_OPTION) == jof.YES_NO_OPTION) {
                Parent quitter;
                try {
                    quitter = FXMLLoader.load(getClass().getResource("menu-view.fxml"));
                    Scene configurationScene = new Scene(quitter);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(configurationScene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    public void choisirJoueur() {
        boolean find= false ;

            if(human.isStart()){
            tourJoueur.setText("its ur turn player"+ human.getName());

            Pawn = ia.getPawn();



            tourJoueur.setText("its ur turn player"+ ia.getName());
            // =ia.getPawn();
            //je recupere la case avec la probabilité plus grand

            inputs=new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
            outputs= net.forwardPropagation(inputs);
            double tab[]=getOutputs(outputs);
                    int i =0;
                while(!find && i< tab.length ) {
                    if (tab[i] == 0 && bouton1.getText() == "") {
                        bouton1.setText(Pawn); find = true;
                    } else if (tab[i] == 1 && bouton2.getText() == "") {
                        bouton2.setText(Pawn);
                        find = true;
                    } else if (tab[i] == 2 && bouton3.getText() == "") {
                        bouton3.setText(Pawn);
                        find = true;
                    } else if (tab[i] == 3 && bouton4.getText() == "") {
                        bouton4.setText(Pawn);
                        find = true;
                    } else if (tab[i] == 4 && bouton5.getText() == "") {
                        bouton5.setText(Pawn);
                        find = true;
                    } else if (tab[i] == 5 && bouton6.getText() == "") {
                        bouton6.setText(Pawn);
                        find = true;
                    } else if (tab[i] == 6 && bouton7.getText() == "") {
                        bouton7.setText(Pawn);
                        find = true;
                    } else if (tab[i] == 7 && bouton8.getText() == "") {
                        bouton8.setText(Pawn);
                        find = true;
                    } else if (tab[i] == 8 && bouton9.getText() == "") {
                        bouton9.setText(Pawn);
                        find = true;
                    }
                    i++;
                    System.out.println("cest fait");



                    }


            }








    }
    public void gameScore(Button b1, Button b2, Button b3) {
        if (b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b3.getText().isEmpty()) {

            fin = true;
            tourJoueur.setText("Fin de Partie !!!");
            String val = b1.getText();
            int score = 0;
            List<Label> p = new ArrayList<>();

            if (val.equalsIgnoreCase("O")) {
                cpt1++;
                score = cpt1;
                p = infojour1;
            } else {
                cpt2++;
                score = cpt2;
                p = infoIa;
            }

            if (ia.getPawn() == val) {
                ia.setScore(score);
                p.get(0).setText(ia.getName());
                p.get(1).setText(ia.getScore() + "");


                tourJoueur.setText("Le gagnant est " + ia.getName());
            } else if (human.getPawn() == val) {
                human.setScore(score);
                p.get(0).setText(human.getName());
                p.get(1).setText(human.getScore() + "");

                tourJoueur.setText("Le gagnant est " + human.getName());
            }
        }
    }
    public void checkWin(){
            gameScore(bouton1,bouton2,bouton3);
            gameScore(bouton4,bouton5,bouton6);
            gameScore(bouton7,bouton8,bouton9);
            gameScore(bouton1,bouton4,bouton7);
            gameScore(bouton2,bouton5,bouton8);
            gameScore(bouton3,bouton6,bouton9);
            gameScore(bouton1,bouton5,bouton9);
            gameScore(bouton3,bouton5,bouton7);


        }










    @FXML

        public void reprendre(ActionEvent event) {
            fin= false ;
            for(Button b :listButtons) {b.setText("");b.setStyle("");}
            if(human.start){

                tourJoueur.setText(human.getName().concat("cest à vous de jouer "));
            }
            else {
                tourJoueur.setText(ia.getName().concat("cest à vous de jouer "));}




    }
    @FXML
    public void jouer(ActionEvent event){

        Button btn;
        btn = (Button) event.getSource();

        if(btn.getText() != "O" && btn.getText() != "X" && !fin) {
            btn.setText(human.getPawn());
            choisirJoueur();
            checkWin();

            if(btn.getText() == "O") {
                btn.setStyle("-fx-text-fill: green;-fx-font-size: 30px;");
            }else if(btn.getText() == "X") {
                btn.setStyle("-fx-text-fill: red;-fx-font-size: 34px;");


            }
        }}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infojour1.add(nom1);
        infojour1.add(pion1);
        infojour1.add(score1);

       // les labels de l'ia
        infoIa.add(nom2);
        infoIa.add(score2);
        infoIa.add(pion2);

        if(human.isStart()) {tourJoueur.setText(human.getName().concat("its your turn"));}
        else if (ia.isStart()){
            tourJoueur.setText(ia.getName().concat("its your turn"));}

        nom1.setText(human.getName());
        score1.setText(String.valueOf(human.getScore()));
       nom2.setText(String.valueOf(ia.getScore()));

        score2.setText(String.valueOf(ia.getScore()));
        listButtons.add(bouton1);
        listButtons.add(bouton2);
        listButtons.add(bouton3);
        listButtons.add(bouton4);
        listButtons.add(bouton5);  ;
        listButtons.add(bouton6);
        listButtons.add(bouton7);
        listButtons.add(bouton8);
        listButtons.add(bouton9);

//
        path=humanVsIaController.path;
        net =humanVsIaController.mlp;
        inputs=new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        outputs=net.forwardPropagation(inputs);
        System.out.println(path);   System.out.println(Arrays.toString(outputs));



    }
     void getMaxindice(){


    }

    //je recupere lindice du plus grand nombre de mon tableau
     int getindiceMaxvalueofBord(double[]outputs){
        double max= outputs[0];
        int indice=0;
        for(int i = 0; i< outputs.length; i++){
            if( max< outputs[i]){
                max =outputs[i];
                    indice =i;
               ;

            }

        }
         return indice ;
    }
    //ici je recupere un tableau trie par odre croissant des intice de outpout;
    public double [] getOutputs(double [] outputs){
        double [] Tab = new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

        for(int i =0; i<outputs.length;i++ ){
            int j = getindiceMaxvalueofBord(outputs);
                Tab[i]=j;
                outputs[j]=0;
        }
        return Tab;

    }


}
