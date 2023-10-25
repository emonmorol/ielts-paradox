package com.example.ielts_paradox.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
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

    @FXML
    private AnchorPane mainStoryPane;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/stories/allStory.fxml"));
            ScrollPane paneee = fxmlLoader.load();
            mainStoryPane.getChildren().add(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void addStoryHandler(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/stories/addStory.fxml"));
            AnchorPane paneee = fxmlLoader.load();
            mainStoryPane.getChildren().add(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void allStoryHandler(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/stories/allStory.fxml"));
            ScrollPane paneee = fxmlLoader.load();
            mainStoryPane.getChildren().add(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
