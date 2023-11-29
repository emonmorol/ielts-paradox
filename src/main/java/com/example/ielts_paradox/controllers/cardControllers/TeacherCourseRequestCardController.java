package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.Alerts.DeleteAlert;
import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.models.PaidStudentInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TeacherCourseRequestCardController {
    @FXML
    private Label date;

    @FXML
    private Label email;

    @FXML
    private Label id_;

    @FXML
    private Label number;

    @FXML
    private Label transection;

    @FXML
    void approveHandler(ActionEvent event) {
        String id = id_.getText();
        boolean isApproved = new ForEnrollment().updateApproval(id);
        if(isApproved){
            SuccessAlert.displayCustomAlert();
        }else{
            ErrorAlert.displayCustomAlert("Failed!","Approval Failed\nTry Again!");
        }
    }

    @FXML
    void declineHandler(ActionEvent event) {
        DeleteAlert.displayCustomAlert("Paid Student",id_.getText());
    }
    public void setDate(PaidStudentInfo psi){
        date.setText(psi.enrollementDate);
        email.setText(psi.email);
        id_.setText(psi._id+"");
        number.setText(psi.bkashNumber);
        System.out.println(psi.transectionId);
        transection.setText(psi.transectionId);
    }
}
