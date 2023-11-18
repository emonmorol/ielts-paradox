package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.StoryInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PopularStoryCardController {
    @FXML
    private Label band;

    @FXML
    private Label studentName;
    @FXML
    private Label id;

    public void setData(StoryInfo bi){
        studentName.setText(bi.studentName);
        band.setText("Band Score: "+bi.bandScore);
        id.setText(bi._id);
    }
}
