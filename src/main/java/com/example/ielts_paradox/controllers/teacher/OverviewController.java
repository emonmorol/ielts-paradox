package com.example.ielts_paradox.controllers.teacher;

import com.example.ielts_paradox.controllers.cardControllers.TeacherOverviewMyCourseCardController;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.LoadDashboardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;

import com.example.ielts_paradox.utils.LoadDashboardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OverviewController implements Initializable{

    @FXML
    private VBox myBlogsContainer;

    @FXML
    AnchorPane mainAnchor ;

    @FXML
    private VBox providedCourseContainer;

    @FXML
    private VBox studentRequestContainer;

    @FXML
    private VBox testRequestContainer;

    @FXML
    private VBox upcomingExamRequestContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();

        ArrayList<CourseInfo> cis = new ForCourse().teacherCourses(info.email,2);

        for (CourseInfo ci:cis){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/teacherOverviewMyCourseCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                TeacherOverviewMyCourseCardController tomccc = fxmlLoader.getController();
                tomccc.setData(ci);
                providedCourseContainer.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i<2;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/teacherOverviewMyBlogsCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                myBlogsContainer.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i<3;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/studentRequestCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                studentRequestContainer.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i<3;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/testRequestCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                testRequestContainer.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i<3;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/upcomingExamCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                upcomingExamRequestContainer.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void goToAllBlogs(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/blogs.fxml");
        mainAnchor.getChildren().add(panel);
    }

    @FXML
    void goToAllCourse(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/my_classes.fxml");
        mainAnchor.getChildren().add(panel);

    }
}