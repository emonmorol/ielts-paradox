package com.example.ielts_paradox.controllers.alertController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;

public class SuccessAlertController {
    @FXML
    private Stage dialogStage;

//    @FXML
//    private Label messageLabel;
//
//    @FXML
//    private Label title;
    @FXML
    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    @FXML
    void okClick(ActionEvent event) {
        dialogStage.close();
    }







}
