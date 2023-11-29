package com.example.ielts_paradox.controllers.teacher;

import com.example.ielts_paradox.controllers.cardControllers.StudentRequestCardController;
import com.example.ielts_paradox.controllers.cardControllers.TeacherOverviewMyBlogsCardController;
import com.example.ielts_paradox.controllers.cardControllers.TeacherOverviewMyCourseCardController;
import com.example.ielts_paradox.controllers.cardControllers.TeacherTestRequestCardController;
import com.example.ielts_paradox.database.ForBlogs;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.*;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.LoadDashboardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @FXML
    private Label takenMockTest;

    @FXML
    private Label totalBlogs;

    @FXML
    private Label totalCourse;

    @FXML
    private Label totalStudents;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();

        int blogCount = new ForBlogs().teacherBlogsCount(info.email);
        totalBlogs.setText(blogCount+"");

        int courseCount = new ForCourse().teacherCourseCount(info.email);
        totalCourse.setText(courseCount+"");

        int exams = new ForTest().takeTestCount(info.email);
        takenMockTest.setText(exams+"");

        int students = new ForEnrollment().totalApprovedStudentCount(info.email);
        totalStudents.setText(students+"");

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

        ArrayList<BlogInfo> bis = new ForBlogs().teacherBlogs(info.email,2);
        for (BlogInfo bi: bis){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/teacherOverviewMyBlogsCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                TeacherOverviewMyBlogsCardController tombcc = fxmlLoader.getController();
                tombcc.setData(bi);
                myBlogsContainer.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<StudentRequest> srs = new ForEnrollment().studentRequestUsingTeacherMail(info.email,5);
        for (StudentRequest sr : srs){
            System.out.println(sr.studentMail+" "+sr.courseTitle+" "+sr._id);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/studentRequestCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                StudentRequestCardController srcc = fxmlLoader.getController();
                srcc.setData(sr);
                studentRequestContainer.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<TestInfo> tis = new ForTest().teachersTests(info.email,3);
        for (TestInfo ti:tis){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/testRequestCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                TeacherTestRequestCardController ttrcc = fxmlLoader.getController();
                ttrcc.setData(ti);
                testRequestContainer.getChildren().add(paneee);

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