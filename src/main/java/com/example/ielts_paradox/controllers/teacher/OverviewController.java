package com.example.ielts_paradox.controllers.teacher;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println();
        for (int i = 0; i<2;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/overviewEnrolledCourseCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                providedCourseContainer.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i<2;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/overviewPopularCourseCard.fxml"));
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
}