package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.database.ForUser;
import com.example.ielts_paradox.models.PaidStudentInfo;
import com.example.ielts_paradox.models.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TeacherApprovedStudentsTableCardController {

    @FXML
    private Button banButton;


    @FXML
    private Label contact;

    @FXML
    private Label date;

    @FXML
    private Label mail;

    @FXML
    private Label name;

    @FXML
    private Label id_;

    @FXML
    void banHandler(ActionEvent event) {
        boolean isDone = new ForEnrollment().banStudent(id_.getText());
        if(isDone){
            banButton.setDisable(true);
            banButton.setText("Banned");
            SuccessAlert.displayCustomAlert();
        }else{
            ErrorAlert.displayCustomAlert("Failed","Couldn't Ban the Student\nTry Again!");
        }
    }
    public void setData(PaidStudentInfo psi){

        id_.setText(psi._id+"");

        if(psi.isExpired){
            banButton.setDisable(true);
            banButton.setText("Banned");
        }
        date.setText(psi.enrollementDate);
        UserInfo ui = new ForUser().getUser(psi.email);
        contact.setText(ui.contactNumber);
        mail.setText(ui.email);
        name.setText(ui.fullName);
    }
}
