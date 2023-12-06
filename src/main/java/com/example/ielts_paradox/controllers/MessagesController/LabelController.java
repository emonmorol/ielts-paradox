package com.example.ielts_paradox.controllers.MessagesController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LabelController {
    @FXML
    private Label msgLabel;

    public void setMsgLabel(String msgLabelS) {
        msgLabel.setText(msgLabelS);
    }
}
