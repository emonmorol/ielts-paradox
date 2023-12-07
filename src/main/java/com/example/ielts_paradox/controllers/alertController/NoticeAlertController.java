package com.example.ielts_paradox.controllers.alertController;

import com.example.ielts_paradox.models.NoticeInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NoticeAlertController {

    @FXML
    private Button close;

    @FXML
    private Label email;

    @FXML
    private Label modulename;

    @FXML
    private Label name;

    @FXML
    private Label noticeField;

    @FXML
    private Label title;

    @FXML
    private Stage dialogStage;

    @FXML
    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    public void setData( NoticeInfo nic){
        title.setText(nic.title);
        noticeField.setText(nic.text);
        name.setText(nic.senderName);
        modulename.setText(nic.module);
        email.setText(nic.senderMail);

    }


    @FXML
    void closeAlert(ActionEvent event) {
        dialogStage.close();
    }

}
