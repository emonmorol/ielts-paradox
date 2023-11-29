package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.Alerts.DeleteAlert;
import com.example.ielts_paradox.models.BlogInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MyBlogsCardController {
    @FXML
    private Label id_;

    @FXML
    private Label date;

    @FXML
    private Label title;

    public void setDate(BlogInfo bi){
        id_.setText(bi._id);
        date.setText(bi.date);
        title.setText(bi.title);
    }

    @FXML
    void deleteHandler(ActionEvent event) {

        DeleteAlert.displayCustomAlert("Blog",id_.getText());
    }
}
