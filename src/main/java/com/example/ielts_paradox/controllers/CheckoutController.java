package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckoutController {
    @FXML
    public Label courseTitle;
    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;
    public static CourseInfo ci ;
    @FXML
    public void backButtonHandler(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/offeredCourseDetails.fxml"));
        root = fxmlLoader.load();
        CourseDetailsController cdc = fxmlLoader.getController();
        cdc.setDetailsInfo(ci);
        scene = new Scene(root);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void setData(CourseInfo cf){
        ci = cf;
        courseTitle.setText(ci.title);
    }
}
