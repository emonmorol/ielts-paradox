package com.example.ielts_paradox.controllers.alertController;

import com.example.ielts_paradox.Alerts.SuccessAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeacherResultEditAlertController {

    @FXML
    private Stage stage;
    private Parent root;
    private Scene scene;
    private Stage dialogStage;

    @FXML
    private TextField bandScore;

    @FXML
    private TextField resultLink;

    @FXML
    void upload(ActionEvent event) {
        System.out.println(bandScore.getText());
        System.out.println(resultLink.getText());
        SuccessAlert.displayCustomAlert();

    }
    @FXML
    public void closeAlert() {
        dialogStage.close();
    }

    @FXML
    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

}
