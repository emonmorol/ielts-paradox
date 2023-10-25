package com.example.ielts_paradox.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseOfferingController implements Initializable {

    @FXML
    private VBox firstBox,secondBox;
    ArrayList<String> it = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");


        boolean isFirst = true;
        for(String i:it){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/courseOfferingCard.fxml"));
                VBox paneee = fxmlLoader.load();
                if(isFirst){
                    firstBox.getChildren().add(paneee);
                    isFirst = false;
                }else{
                    secondBox.getChildren().add(paneee);
                    isFirst= true;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
