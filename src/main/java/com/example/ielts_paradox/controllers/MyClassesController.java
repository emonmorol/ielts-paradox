package com.example.ielts_paradox.controllers;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyClassesController implements Initializable{

    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/courses/my_courses.fxml"));
        try {
            MFXScrollPane paneee = fxmlLoader.load();
            mainPane.getChildren().add(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void addCourseHandler(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/courses/courseForms/formContainer.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
            if(mainPane!=null)
                mainPane.getChildren().clear();
            mainPane.getChildren().add(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void myCourseHandler(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/courses/my_courses.fxml"));
        try {
            MFXScrollPane paneee = fxmlLoader.load();
            if(mainPane!=null)
                mainPane.getChildren().clear();
            mainPane.getChildren().add(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
