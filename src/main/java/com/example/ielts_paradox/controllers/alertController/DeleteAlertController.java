package com.example.ielts_paradox.controllers.alertController;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.*;
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
        }else if(type=="Course"){
            isDone = new ForCourse().deleteCourse(_id);
        }else if(type=="Paid Student"){
            isDone = new ForEnrollment().declineRequest(_id);
        }
        else if(type=="Test"){
            isDone = new ForTest().declineRequest(_id);
        }


        if(isDone){
            SuccessAlert.displayCustomAlert();
        }else{
            ErrorAlert.displayCustomAlert("Delete Failed","Something Went Wrong!\nTry Again\n");
        }

        dialogStage.close();

    }
}

