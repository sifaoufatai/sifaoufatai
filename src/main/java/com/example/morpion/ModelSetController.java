package com.example.morpion;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ModelSetController {
    public Label mod1;
    public Label mod2;
    public RadioButton humanvshuman;
    public RadioButton iavshuman;
    public RadioButton easy;
    public RadioButton hard;
    public RadioButton middle;

    public void init(){
        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        iavshuman.setToggleGroup(group1);
        humanvshuman.setToggleGroup(group1);
        easy.setToggleGroup(group2);
        hard.setToggleGroup(group2);
        middle.setToggleGroup(group2);
        iavshuman.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && easy.isSelected()) {
                System.out.println("Both buttons selected");
                // ajoutez ici l'action que vous souhaitez effectuer lorsque les deux boutons sont sélectionnés
            }
        });
        easy.setOnAction(e -> {
            if (iavshuman.isSelected()) {
                System.out.println("Both buttons selected");
                // ajoutez ici l'action que vous souhaitez effectuer lorsque les deux boutons sont sélectionnés
            }
        });

    }
}
