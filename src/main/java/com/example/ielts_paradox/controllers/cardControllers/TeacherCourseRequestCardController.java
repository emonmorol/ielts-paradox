package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.Alerts.AcceptAlert;
import com.example.ielts_paradox.Alerts.DeleteAlert;
import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.database.ForNotices;
import com.example.ielts_paradox.models.NoticeInfo;
import com.example.ielts_paradox.models.PaidStudentInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
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
    String courseTitle;
    PaidStudentInfo ps;

    @FXML
    void approveHandler(ActionEvent event) {
        String id = id_.getText();
        UserInfo ui = UserSingleTon.getInstance(new UserInfo()).getUser();

        boolean willApprove = AcceptAlert.displayCustomAlert("You want to give access to this student.");
        if(willApprove){
            boolean isApproved = new ForEnrollment().updateApproval(id);
            if(isApproved){
                String text = "We are delighted to inform you that your recent course request has been successfully processed, and you are now officially enrolled in "+courseTitle+". Your commitment to this course is a valuable step in your academic journey, and we are excited to have you as part of our learning community.";
                boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text,"Course Approved!", ps.email, ui.email,ui.fullName,courseTitle));
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed!","Approval Failed\nTry Again!");
            }
        }

    }

    @FXML
    void declineHandler(ActionEvent event) {
        UserInfo ui = UserSingleTon.getInstance(new UserInfo()).getUser();
        boolean isDeleted = DeleteAlert.displayCustomAlert("Paid Student",id_.getText());
        if(isDeleted){
            String text = "I trust this message finds you well. I wanted to inform you that we have processed your recent course request, and unfortunately, we are unable to accommodate your enrollment in "+courseTitle+" for the upcoming semester.";
            boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text,"Course Declined!", ps.email, ui.email,ui.fullName,courseTitle));
        }
    }
    public void setDate(PaidStudentInfo psi,String title){
        date.setText(psi.enrollementDate);
        email.setText(psi.email);
        id_.setText(psi._id+"");
        number.setText(psi.bkashNumber);
        System.out.println(psi.transectionId);
        transection.setText(psi.transectionId);
        courseTitle = title;
        ps = psi;
    }
}
