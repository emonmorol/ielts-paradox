package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.Alerts.NoticeAlert;
import com.example.ielts_paradox.database.ForNotices;
import com.example.ielts_paradox.models.NoticeInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LatestNoticeCardController {

    @FXML
    private Label fullMessage;
    @FXML
    private Label _id;

    @FXML
    private Label title;

    public void setData(NoticeInfo nic){
        title.setText(nic.title);
        fullMessage.setText(nic.text);
        _id.setText(nic._id+"");
    }

    @FXML
    void seeDetails(ActionEvent event) {
        NoticeAlert.displayCustomAlert(new ForNotices().getNoticeById(_id.getText()));

    }
}
