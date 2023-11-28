package com.example.ielts_paradox.controllers.teacher;

import com.example.ielts_paradox.controllers.cardControllers.OverviewEnrolledCourseCardController;
import com.example.ielts_paradox.controllers.cardControllers.OverviewPopularCourseCardController;
import com.example.ielts_paradox.controllers.cardControllers.PopularBlogCardController;
import com.example.ielts_paradox.controllers.cardControllers.PopularStoryCardController;
import com.example.ielts_paradox.database.ForBlogs;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.StoryInfo;
import com.example.ielts_paradox.models.UserInfo;
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
    private VBox providedCourseContainer;

    @FXML
    private VBox studentRequestContainer;

    @FXML
    private VBox testRequestContainer;

    @FXML
    private VBox upcomingExamRequestContainer;

    @FXML
    private Label greetings;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo u = user.getUser();
        greetings.setText("Hello "+u.fullName +", Welcome Back.");
        System.out.println(u.email);
        ArrayList<CourseInfo> it = new ForCourse().teacherCourses(u.email,2);

        for (CourseInfo c: it){
            System.out.println(c.title);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/teacherOverviewEnrolledCourseCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                providedCourseContainer.getChildren().add(paneee);
                OverviewEnrolledCourseCardController oeccc = fxmlLoader.getController();
                oeccc.setData(c);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//        ArrayList<BlogInfo> it2 = new ForBlogs().teacherBlogs(u.email,2);
//        for (BlogInfo c: it2){
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/overviewPopularCourseCard.fxml"));
//            try {
//                AnchorPane paneee = fxmlLoader.load();
//                myBlogsContainer.getChildren().add(paneee);
//                OverviewPopularCourseCardController opccc = fxmlLoader.getController();
//                opccc.setData(c);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

        for (int i = 0; i<2;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/teacherOverviewPopularCourseCard.fxml"));
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

//
//        ArrayList<BlogInfo> it3 = new ForBlogs().getAllBlog(0,2);
//        for (BlogInfo c: it3){
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/popularBlogCard.fxml"));
//            try {
//                AnchorPane paneee = fxmlLoader.load();
//                popularBlog.getChildren().add(paneee);
//                PopularBlogCardController pbcc = fxmlLoader.getController();
//                pbcc.setData(c);
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        ArrayList<StoryInfo> it4 = new ForStories().getAllStories(0,2);
//        for (StoryInfo c: it4){
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/popularStoryCard.fxml"));
//            try {
//                AnchorPane paneee = fxmlLoader.load();
//                popularStory.getChildren().add(paneee);
//                PopularStoryCardController pscc = fxmlLoader.getController();
//                pscc.setData(c);
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i<3;i++){
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/upcomingExamCard.fxml"));
//            try {
//                AnchorPane paneee = fxmlLoader.load();
//                upcomingExam.getChildren().add(paneee);
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}