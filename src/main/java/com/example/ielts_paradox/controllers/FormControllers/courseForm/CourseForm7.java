package com.example.ielts_paradox.controllers.FormControllers.courseForm;

import com.example.ielts_paradox.Alerts.AcceptAlert;
import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForCourse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CourseForm7 {
    @FXML
    void uploadCourseHandler(ActionEvent event) {
        boolean isAccepted = AcceptAlert.displayCustomAlert("Once you upload the course you wont be abe the change it again.");
        if(isAccepted){
            boolean isUploaded = new ForCourse().uploadCourse(AddCourseController.title,AddCourseController.price,AddCourseController.discount,AddCourseController.details,AddCourseController.features,AddCourseController.curriculum,AddCourseController.faqs,AddCourseController.sidebarPoint,AddCourseController.thumbnail,AddCourseController.routine,AddCourseController.messagePort,AddCourseController.content);
            if(isUploaded){
                SuccessAlert.displayCustomAlert();
            }else {
                ErrorAlert.displayCustomAlert("Upload Failed!","Something went wrong on our side!\nTry again!");
            }
        }
    }
}
