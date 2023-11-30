package com.example.ielts_paradox.controllers.MockTestController;

import com.example.ielts_paradox.Alerts.DeleteAlert;
import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.TestInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TestRequestCardController {
    @FXML
    private Label bkashNumber;

    @FXML
    private Label email;

    @FXML
    private Label enrollmentDate;

    @FXML
    private Label transectionId;

    @FXML
    private Label id_;

    @FXML
    void approveHandler(ActionEvent event) {
        boolean isUpdated = new ForTest().updateTestApproval(id_.getText());
        if(isUpdated){
            SuccessAlert.displayCustomAlert();
        }else{
            ErrorAlert.displayCustomAlert("Failed","Can't Approve Request\nTry Again!");
        }
    }

    @FXML
    void declineHandler(ActionEvent event) {
        DeleteAlert.displayCustomAlert("Test",id_.getText());
    }

    public void setData(TestInfo ti){
        id_.setText(ti._id);
        transectionId.setText(ti.transectionId);
        bkashNumber.setText(ti.bkashNumber);
        email.setText(ti.studentMail);
        enrollmentDate.setText(ti.enrollmentDate);
    }
}
