package com.example.ielts_paradox.controllers.teacher;

import com.example.ielts_paradox.controllers.MockTestController.MockTestTableController;
import com.example.ielts_paradox.controllers.MockTestController.TestRequestController;
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
    void goToApprovedStudents(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxmls/teacher/mocktest/mockTestTable.fxml"));
        this.root = (Parent)fxmlLoader.load();
//        MockTestTableController mttc = fxmlLoader.getController();

        this.scene = new Scene(this.root);
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    void goToListeningReq(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxmls/teacher/mocktest/testRequestTable.fxml"));
        this.root = (Parent)fxmlLoader.load();
        TestRequestController ltc = fxmlLoader.getController();
        ltc.setData("Listening","0");
        this.scene = new Scene(this.root);
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    void goToReadingReq(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxmls/teacher/mocktest/testRequestTable.fxml"));
        this.root = (Parent)fxmlLoader.load();
        TestRequestController ltc = fxmlLoader.getController();
        ltc.setData("Reading","0");
        this.scene = new Scene(this.root);
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    void goToSpeakingReq(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxmls/teacher/mocktest/testRequestTable.fxml"));
        this.root = (Parent)fxmlLoader.load();
        TestRequestController ltc = fxmlLoader.getController();
        ltc.setData("Speaking","0");
        this.scene = new Scene(this.root);
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    void goToWrittingReq(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxmls/teacher/mocktest/testRequestTable.fxml"));
        this.root = (Parent)fxmlLoader.load();
        TestRequestController ltc = fxmlLoader.getController();
        ltc.setData("Writing","0");
        this.scene = new Scene(this.root);
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
