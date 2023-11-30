package com.example.ielts_paradox.controllers.student;

import com.example.ielts_paradox.Alerts.ExamAlert;
import com.example.ielts_paradox.controllers.CourseDetailsController;
import com.example.ielts_paradox.controllers.ExamPageController.StudentExamPageController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MockTestController {

    @FXML
    private Label pageTitle;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void onClickListening(ActionEvent event) throws IOException {
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();

        boolean isEnrolled = new ForTest().testValidation(info.email,"Listening");
        if(!isEnrolled){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            sdc.onClickPricing(event);

        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/exam_page.fxml"));
            root = fxmlLoader.load();
            StudentExamPageController sepc = fxmlLoader.getController();
            sepc.setData(new ForTest().getTestInfoForStudent("Listening",info.email));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void onClickReading(ActionEvent event) throws IOException {
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();

        boolean isEnrolled = new ForTest().testValidation(info.email,"Reading");
        if(!isEnrolled){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            sdc.onClickPricing(event);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/exam_page.fxml"));
            root = fxmlLoader.load();
            StudentExamPageController sepc = fxmlLoader.getController();
            sepc.setData(new ForTest().getTestInfoForStudent("Reading",info.email));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void onClickSpeaking(ActionEvent event) throws IOException {
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();

        boolean isEnrolled = new ForTest().testValidation(info.email,"Speaking");
        if(!isEnrolled){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            sdc.onClickPricing(event);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/exam_page.fxml"));
            root = fxmlLoader.load();
            StudentExamPageController sepc = fxmlLoader.getController();
            sepc.setData(new ForTest().getTestInfoForStudent("Speaking",info.email));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void onClickWritting(ActionEvent event) throws IOException {
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();

        boolean isEnrolled = new ForTest().testValidation(info.email,"Writing");
        if(!isEnrolled){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            sdc.onClickPricing(event);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/exam_page.fxml"));
            root = fxmlLoader.load();
            StudentExamPageController sepc = fxmlLoader.getController();
            sepc.setData(new ForTest().getTestInfoForStudent("Writing",info.email));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }



}
