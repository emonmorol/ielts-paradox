package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.Alerts.DeleteAlert;
import com.example.ielts_paradox.models.StoryInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MyStoryCardController {

    @FXML
    private Label id_;

    @FXML
    private Label score;

    @FXML
    private Label studentName;

    public void setData(StoryInfo si){
        id_.setText(si._id);
        score.setText(si.bandScore);
        studentName.setText(si.studentName);
    }
    @FXML
    public void deleteHandler(ActionEvent event) {
        DeleteAlert.displayCustomAlert("Story",id_.getText());
    }

}
