package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.controllers.cardControllers.CourseOfferingCardController;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.utils.LoadDashboardPane;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseDetailsController {

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

    public void setDetailsInfo(CourseInfo crs){
        CourseDetailsController.ci = crs;
        courseTitle.setText(ci.title);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/description.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
            scrollpane.setContent(paneee);
            System.out.println(insideAnchor+" "+paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void backButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
        root = fxmlLoader.load();
        StudentDashboardController tdc =fxmlLoader.getController();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        tdc.onClick2(event);
        stage.show();
    }

    @FXML
    public void descriptionHandler(ActionEvent event) {
        System.out.println(ci.title);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/description.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
//            insideAnchor.getChildren().add(paneee);
            scrollpane.setContent(paneee);
            System.out.println(insideAnchor+" "+paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void faqHandler(ActionEvent event) {
        System.out.println(ci.title);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/faq.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
//            insideAnchor.getChildren().add(paneee);
            scrollpane.setContent(paneee);
            System.out.println(insideAnchor+" "+paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void instructorHandler(ActionEvent event) {
        System.out.println(ci.title);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/instructor.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
//            insideAnchor.getChildren().add(paneee);
            scrollpane.setContent(paneee);
            System.out.println(insideAnchor+" "+paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void routineHandler(ActionEvent event) {
        System.out.println("from routing = "+ci.title);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/routine.fxml"));
        try {
            AnchorPane paneee = fxmlLoader.load();
//            insideAnchor.getChildren().add(paneee);
            scrollpane.setContent(paneee);
            System.out.println(insideAnchor+" "+paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void enrollNowHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/checkout.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
