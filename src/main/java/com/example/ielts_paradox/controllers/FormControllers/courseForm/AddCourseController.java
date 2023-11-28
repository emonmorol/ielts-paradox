package com.example.ielts_paradox.controllers.FormControllers.courseForm;

import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.utils.LoadDashboardPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCourseController implements Initializable {

    public static CourseInfo course = new CourseInfo();

    @FXML
    private ProgressBar formProgress;

    @FXML
    private Label id_;
    @FXML
    private Label id_1;
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
        }
        else if(id==2){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/3rd_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("3");
        }
        else if(id==3){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/4th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("4");
        }
        else if(id==4){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/5th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("5");
        }
        else if(id==5){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/6th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("6");
        }
        else if(id==6){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/7th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("7");
            next.setDisable(true);
        }

        double progress = Math.ceil(id*14.29);
        formProgress.setProgress(progress);
        id_1.setText(id_.getText()+"/7");
        System.out.println(formProgress.getProgress());

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
        }
        else if(id==6){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/5th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("5");
        }
        else if(id==5){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/4th_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("4");
        }
        else if(id==4){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/3rd_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("3");
        }
        else if(id==3){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/2nd_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("2");
        }
        else if(id==2){
            AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/1st_page.fxml");
            formPane.getChildren().clear();
            formPane.getChildren().add(pane);
            id_.setText("1");
            prev.setDisable(true);
        }

        double progress = Math.ceil(id*14.29);
        formProgress.setProgress(progress);
        id_1.setText(id_.getText()+"/7");
        System.out.println(formProgress.getProgress());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane pane = new LoadDashboardPane().getSidePane("/fxmls/teacher/pages/courses/courseForms/1st_page.fxml");
        formPane.getChildren().clear();
        formPane.getChildren().add(pane);
        int id = Integer.parseInt(id_.getText());
        formProgress.setStyle("-fx-accent:red;");
        if(id==1){
            prev.setDisable(true);
        }
    }
}
