package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.Alerts.Alert2;
import com.example.ielts_paradox.utils.Alert2Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable{
    @FXML
    private Circle circle;
    Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void emailEditor(ActionEvent event) {
        Alert2.displayCustomAlert("Hello From Email","LOL");
    }
}
