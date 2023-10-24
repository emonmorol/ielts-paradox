package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.utils.LoadDashboardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class OverviewController {
    @FXML
    AnchorPane mainAnchor;
    @FXML
    public void onClickSeeCourse(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/courses.fxml");
        mainAnchor.getChildren().add(panel);
//        mainPane.setCenter(panel);
    }
}
