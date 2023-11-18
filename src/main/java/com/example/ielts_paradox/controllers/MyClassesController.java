package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyClassesController implements Initializable{

    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();

        FXMLLoader fxmlLoader = new FXMLLoader();

        try {
            if(info.isTeacher){
                fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/my_courses.fxml"));
                AnchorPane paneee = fxmlLoader.load();
                mainPane.getChildren().add(paneee);
            }else{
                fxmlLoader.setLocation(getClass().getResource("/fxmls/students/pages/my_courses.fxml"));
                MFXScrollPane paneee = fxmlLoader.load();
                mainPane.getChildren().add(paneee);
            }
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
        fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/my_courses.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
            if(mainPane!=null)
                mainPane.getChildren().clear();
            mainPane.getChildren().add(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
