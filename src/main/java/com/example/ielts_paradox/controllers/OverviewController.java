package com.example.ielts_paradox.controllers;

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
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    @FXML
    AnchorPane mainAnchor ;
    @FXML
    private VBox enrolledCourse;
    @FXML
    private VBox popularCourse;
    @FXML
    private HBox popularBlog;
    @FXML
    private HBox popularStory;
    @FXML
    public void onClickSeeCourse(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/courses.fxml");
        mainAnchor.getChildren().add(panel);
    }

    @FXML
    public void onClickEnrollCourse(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/my_classes.fxml");
        mainAnchor.getChildren().add(panel);
    }

    @FXML
    void onClickBlogs(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/blogs.fxml");
        mainAnchor.getChildren().add(panel);
    }



    @FXML
    void onClickPopularCourses(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/courses.fxml");
        mainAnchor.getChildren().add(panel);
    }



    @FXML
    void onClickSuccessStories(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/success_stories.fxml");
        mainAnchor.getChildren().add(panel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i<2;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/overviewEnrolledCourseCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                enrolledCourse.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i<2;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/overviewPopularCourseCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                popularCourse.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i<2;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/popularBlogCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                popularBlog.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i<2;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/popularStoryCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                popularStory.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}