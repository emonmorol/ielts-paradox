package com.example.ielts_paradox.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import io.github.palexdev.materialfx.controls.MFXButton;

public class SuccessStoriesController implements Initializable {


//    @FXML
    public VBox firstBox,secondBox;

    //    @FXML
//    public BorderPane mainPane;
    @FXML
    public AnchorPane anchor;
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
                fxmlLoader.setLocation(getClass().getResource("/fxmls/students/cards/stories_card.fxml"));
                AnchorPane paneee = fxmlLoader.load();
                if(isFirst){
                    firstBox.getChildren().add(paneee);
                    isFirst = false;
                }
                else{
                    secondBox.getChildren().add(paneee);
                    isFirst= true;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
    @FXML
    public void OnBTN(ActionEvent e){

    }
    @FXML
    public void onCl(ActionEvent e){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/full_blogs.fxml"));
            AnchorPane paneee = fxmlLoader.load();
            anchor.getChildren().add(paneee);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
