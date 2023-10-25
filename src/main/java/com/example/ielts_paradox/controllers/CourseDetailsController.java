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

public class CourseDetailsController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxmls/cards/courseOfferingCard.fxml"));
        try {
            Parent root = loader.load();
            CourseOfferingCardController cocd = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//       FXMLLoader fxmlLoader = new FXMLLoader();
//       fxmlLoader.setLocation(getClass().getResource("/fxmls/students/courseDetails/description.fxml"));
//       try {
//            AnchorPane paneee = fxmlLoader.load();
//            scrollpane.setContent(paneee);
//            System.out.println(insideAnchor+" "+paneee);
//       } catch (IOException e) {
//            throw new RuntimeException(e);
//       }
    }
    public void setDetailsInfo(CourseInfo crs){

    }


    @FXML
    void backButtonHandler(ActionEvent event) throws IOException {
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
    void descriptionHandler(ActionEvent event) {
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
    void faqHandler(ActionEvent event) {
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
    void instructorHandler(ActionEvent event) {
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
    void routineHandler(ActionEvent event) {
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
}
