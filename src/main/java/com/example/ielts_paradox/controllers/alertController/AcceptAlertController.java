package com.example.ielts_paradox.controllers.alertController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AcceptAlertController {

    @FXML
    private Stage dialogStage;

    @FXML
    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }
    @FXML
    public void ignoreButton(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void acceptButton(ActionEvent event) {

    }



}
