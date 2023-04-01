package com.example.morpion;

import ia.Coup;
import ia.MultiLayerPerceptron;
import ia.SigmoidalTransferFunction;
import ia.Test;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

class ProgessBarContoller implements Initializable {

    // Apprentissage
    public static MultiLayerPerceptron net;
    private static int currentEpoch;
    public Task task;
    public Label labelTest;
    public ProgressBar pb;
    Test test1;

    public String s;
    public int h;
    public double lr;
    public int lh;
    public String path;

    @FXML
    public void pressStartButton(ActionEvent e) {
        try {

            int size = 9;
            System.out.println("START TRADING.....");
            //
            int[] layers = new int[] { size, 1, size };

            System.out.println("---");
            System.out.println("Load data ...");
            HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("resources/train/train.txt");

            System.out.println("---");

            if (this.net == null) {
                this.net = new MultiLayerPerceptron(layers, 0.1, new SigmoidalTransferFunction());
                this.currentEpoch = 0;
            }
            net.save(path);

            pb.setProgress(0);
            //
            // String s="";
            task = getTask(mapTrain, path);
            pb.progressProperty().bind(task.progressProperty());

            //
            task.messageProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    System.out.println(newValue);
                    // tf.setText("bonjour");
                    labelTest.setText(newValue);
                }
            });

            //
            Thread thread = new Thread(this.task);
            thread.start();



        } catch (Exception ex) {
            System.out.println("Test.test()");
            ex.printStackTrace();
            System.exit(-1);
        }
    }


    public MultiLayerPerceptron learn(int size, int h, double lr, int l, String chemin) {
        MultiLayerPerceptron aux = null;
        try {

            System.out.println("START TRADING.....");
            //
            int x1 = l + 2;
            int[] layers = new int[x1];
            layers[0] = size;
            for (int i = 1; i < x1 - 1; i++) {
                layers[i] = (int) h;
            }
            layers[x1 - 1] = size;

            System.out.println("---");
            System.out.println("Load data ...");
            HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("resources/train/train.txt");

            System.out.println("---");

            pb.setProgress(0);
            //
            task = getTask(mapTrain, chemin);
            pb.progressProperty().bind(task.progressProperty());

            //
            task.messageProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    System.out.println(newValue);

                    labelTest.setText(newValue);
                }
            });

            new Thread(this.task).start();
            aux =  (MultiLayerPerceptron) task.getValue();
        }
        catch(Exception ex) {
            System.out.println("Test.test()");
            ex.printStackTrace();
            System.exit(-1);
        }
        return aux;
    }

    //public Task<Double> getTask(HashMap<Integer, Coup> mapTrain){
    public static  Task<MultiLayerPerceptron> getTask(HashMap<Integer, Coup> mapTrain, String chemin){
        return new Task<MultiLayerPerceptron>() {

            @Override
            protected MultiLayerPerceptron call() throws Exception {
                double epochs = 1000000000 ;
                double error = 0.0 ;
                int onePercent = (int)(epochs/100);

                //TRAINING ...

                for(int i = 0; i < epochs; i++){

                    Coup c = null ;
                    while ( c == null )
                        c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

                    error += net.backPropagate(c.in, c.out);

                    if ( i % onePercent == 0 ) {
                        double p = i/epochs;
                        String pourcentage = String.valueOf((int)(p * 100));
                        String e = String.valueOf((error/(double)i));

                        System.out.println("Error at step "+i+" is "+ (error/(double)i));
                        updateMessage("Error at step"+i+" is "+e+" = "+pourcentage+"%");
                    }
                    if(isCancelled()) {
                        currentEpoch = 1;
                        break;
                    }
                    updateProgress(i, epochs);

                }
                error /= epochs ;
                if ( epochs > 0 ) {
                    //updateMessage("final error is " + df_error.format(error));
                    updateMessage("final error is " + error);
                }
                net.save(chemin);
                return net;
            }
        };
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        h  = humanVsIaController.h;
        lr = humanVsIaController.lr;
        lh = humanVsIaController.lh;
         path = "C://Users//etudiant//Desktop//Morpion//resources//models//mlp_"+h+"_"+lr+"_"+lh+".srl";
    }

}

