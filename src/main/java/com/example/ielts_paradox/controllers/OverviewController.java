package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.utils.LoadDashboardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import com.example.ielts_paradox.utils.LoadDashboardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class OverviewController {
    @FXML
    AnchorPane mainAnchor ;
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

    @FXML
    void onClickUpcomigExam(ActionEvent event) {

    }
}