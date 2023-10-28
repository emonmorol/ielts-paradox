package com.example.ielts_paradox.controllers.FormControllers;

import com.example.ielts_paradox.utils.LoadDashboardPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCourseController implements Initializable {

    @FXML
    private Label id_;
    @FXML
    private MFXButton next;

    @FXML
    private MFXButton prev;

    @FXML
    private AnchorPane formPane;


    @FXML
    void nextHandler(ActionEvent event) {
        int id = Integer.parseInt(id_.getText());
        if(id==1){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/2nd_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("2");
            prev.setDisable(false);
        }else if(id==2){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/3rd_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("3");
        }else if(id==3){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/4th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("4");
        }else if(id==4){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/5th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("5");
        }else if(id==5){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/6th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("6");
        }else if(id==6){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/7th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("7");
            next.setDisable(true);
        }
    }

    @FXML
    void previousHandler(ActionEvent event) {
        int id = Integer.parseInt(id_.getText());
        if(id==7){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/6th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("6");
            next.setDisable(false);
        }else if(id==6){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/5th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("5");
        }else if(id==5){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/4th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("4");
        }else if(id==4){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/3rd_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("3");
        }else if(id==3){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/2nd_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("2");
        }else if(id==2){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/1st_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("1");
            prev.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/1st_page.fxml");
        formPane.getChildren().clear();
        formPane.getChildren().add(pane);
        int id = Integer.parseInt(id_.getText());
        if(id==1){
            prev.setDisable(true);
        }
    }
}
