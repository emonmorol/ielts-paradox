package com.example.ielts_paradox.controllers.alertController;



import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ErrorAlertController {

    @FXML
    private VBox customAlert;

    @FXML
    private Label messageLabel;
    @FXML
    private Label title;

    private Stage dialogStage;

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    public void setTitle(String m) {
        title.setText(m);
    }

    public void closeAlert() {
        dialogStage.close();
    }
}

