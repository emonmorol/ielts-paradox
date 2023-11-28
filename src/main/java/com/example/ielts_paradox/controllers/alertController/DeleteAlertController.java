package com.example.ielts_paradox.controllers.alertController;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForBlogs;
import com.example.ielts_paradox.database.ForProfile;
import com.example.ielts_paradox.database.ForStories;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DeleteAlertController {
    @FXML
    private Stage dialogStage;
    String type;
    String _id;

    @FXML
    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    @FXML
    public void cancel(ActionEvent event) {
        dialogStage.close();
    }

    public void setData(String t,String i){
        this.type = t;
        _id = i;
    }

    @FXML
    public void delete(ActionEvent event) {
        boolean isDone = false;

        if(type=="Story"){
            isDone = new ForStories().deleteStory(_id);
        }else if(type=="Blog"){
            isDone = new ForBlogs().deleteBlog(_id);
        }


        if(isDone){
            SuccessAlert.displayCustomAlert();
        }else{
            ErrorAlert.displayCustomAlert("Delete Failed","Something Went Wrong!\nTry Again\n");
        }

        dialogStage.close();
    }
}

