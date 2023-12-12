package com.example.ielts_paradox.controllers.MockTestController;

import com.example.ielts_paradox.Alerts.AcceptAlert;
import com.example.ielts_paradox.Alerts.DeleteAlert;
import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForNotices;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.NoticeInfo;
import com.example.ielts_paradox.models.TestInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
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
    TestInfo tis;

    @FXML
    void approveHandler(ActionEvent event) {
        UserInfo ui = UserSingleTon.getInstance(new UserInfo()).getUser();
        boolean willApprove = AcceptAlert.displayCustomAlert("You want to Take Exam For this student.");
        if(willApprove) {
            boolean isUpdated = new ForTest().updateTestApproval(id_.getText());
            if (isUpdated) {
                String text = "I hope this message finds you in good spirits. I am pleased to inform you that your recent " + tis.examModule + " Mock exam request has been accepted";
                boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text, "Exam Request Approved!", tis.studentMail, ui.email, ui.fullName, tis.examModule));
                SuccessAlert.displayCustomAlert();
            } else {
                ErrorAlert.displayCustomAlert("Failed", "Can't Approve Request\nTry Again!");
            }
        }
    }

    @FXML
    void declineHandler(ActionEvent event) {
        UserInfo ui = UserSingleTon.getInstance(new UserInfo()).getUser();
        boolean isDeleted = DeleteAlert.displayCustomAlert("Test",id_.getText());
        if(isDeleted){
            String text = "I wanted to inform you that we have processed your recent exam request, and unfortunately, we are unable to accommodate your request to decline the upcoming exam for "+tis.examModule;
            boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text,"Exam Request Declined!", tis.studentMail, ui.email,ui.fullName,tis.examModule));
        }
    }

    public void setData(TestInfo ti){
        tis = ti;
        id_.setText(ti._id);
        transectionId.setText(ti.transectionId);
        bkashNumber.setText(ti.bkashNumber);
        email.setText(ti.studentMail);
        enrollmentDate.setText(ti.enrollmentDate);
    }
}
