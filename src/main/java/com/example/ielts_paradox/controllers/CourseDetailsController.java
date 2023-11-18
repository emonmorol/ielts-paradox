package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.web.WebView;

public class CourseDetailsController implements Initializable{

    @FXML
    private AnchorPane insideAnchor;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;
    @FXML
    private Label courseTitle;

    public static CourseInfo ci ;
    @FXML
    private WebView web_video;
    public static String point;

    public void setDetailsInfo(CourseInfo crs,String p){
        CourseDetailsController.ci = crs;
        CourseDetailsController.point = p;
        courseTitle.setText(ci.title);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/description.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
            scrollpane.setContent(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void backButtonHandler(ActionEvent event) throws IOException {
        UserSingleTon ins = UserSingleTon.getInstance(new UserInfo());
        UserInfo user = ins.getUser();
        if(user.isTeacher){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/teacherDashboard.fxml"));
            root = fxmlLoader.load();
            TeacherDashboardController tdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            if(CourseDetailsController.point == "1")
                tdc.onClick2(event);
            else
                tdc.onClickOverview(event);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            if(CourseDetailsController.point == "1")
                sdc.onClick2(event);
            else
                sdc.onClickOverview(event);
        }

        stage.show();
    }

    @FXML
    public void descriptionHandler(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/description.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
//            insideAnchor.getChildren().add(paneee);
            scrollpane.setContent(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void faqHandler(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/faq.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
            scrollpane.setContent(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void instructorHandler(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/instructor.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
            scrollpane.setContent(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void routineHandler(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/routine.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
            scrollpane.setContent(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void enrollNowHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/checkout.fxml"));
        root = fxmlLoader.load();
        CheckoutController cc = fxmlLoader.getController();
        cc.setData(ci);
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        web_video.getEngine().load("https://www.youtube.com/embed/t1_ZFIGMKy8");
    }
}
