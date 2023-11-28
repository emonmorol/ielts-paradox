package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.MockTestController.ListeningTestRequestController;
import com.example.ielts_paradox.controllers.MockTestController.ReadingTestRequestController;
import com.example.ielts_paradox.controllers.MockTestController.SpeakingTestRequestController;
import com.example.ielts_paradox.controllers.MockTestController.WritingTestRequestController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TeacherTestRequestCardController {

    @FXML
    private Label testName;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;



    public Label getTestName() {
        return testName;
    }

    @FXML
    void goToTestRequestPage(ActionEvent event) throws IOException {
        System.out.println(testName.getText());
        if(Objects.equals(testName.getText(), "SPEAKING")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/speakingTestRequest.fxml"));
            root = fxmlLoader.load();
            SpeakingTestRequestController stc = fxmlLoader.getController();
            stc.setData("1");
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

        else if (Objects.equals(testName.getText(), "READING")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/readingTestRequest.fxml"));
            root = fxmlLoader.load();
            ReadingTestRequestController rtc = fxmlLoader.getController();
            rtc.setData("1");
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else if (Objects.equals(testName.getText(), "WRITTING")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/writingTestRequest.fxml"));
            root = fxmlLoader.load();
            WritingTestRequestController wtc = fxmlLoader.getController();
            wtc.setData("1");
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        else if (Objects.equals(testName.getText(), "LISTENING")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/listeningTestRequest.fxml"));
            root = fxmlLoader.load();
            ListeningTestRequestController ltc = fxmlLoader.getController();
            ltc.setData("1");
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }


    }



    }





