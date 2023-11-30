package com.example.ielts_paradox.controllers.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MockTestController {

    @FXML
    private Label pageTitle;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void onClickListening(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/exam_page.fxml"));
        root = fxmlLoader.load();
//        ListeningTestRequestController ltc = fxmlLoader.getController();
//        ltc.setData("0");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onClickReading(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/exam_page.fxml"));
        root = fxmlLoader.load();
//        ListeningTestRequestController ltc = fxmlLoader.getController();
//        ltc.setData("0");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onClickSpeaking(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/exam_page.fxml"));
        root = fxmlLoader.load();
//        ListeningTestRequestController ltc = fxmlLoader.getController();
//        ltc.setData("0");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onClickWritting(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/exam_page.fxml"));
        root = fxmlLoader.load();
//        ListeningTestRequestController ltc = fxmlLoader.getController();
//        ltc.setData("0");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }



}
