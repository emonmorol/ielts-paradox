package com.example.ielts_paradox.controllers.FormControllers;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.StoryInfo;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AddStoryController {
    @FXML
    private MFXTextField bandScore;

    @FXML
    private TextArea story;

    @FXML
    private MFXTextField studentName;

    @FXML
    public void addStoryHandler(ActionEvent event) {
        StoryInfo si = new StoryInfo(story.getText(),"",studentName.getText(),bandScore.getText());
        Boolean isUpload = new ForStories().uploadStory(si);
        if(isUpload){
            SuccessAlert.displayCustomAlert();
        }else{
            ErrorAlert.displayCustomAlert("Upload Failed!","Something Went Wrong!\nTry Again!");
        }
    }
}
