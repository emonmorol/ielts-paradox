package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.models.CourseInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OverviewEnrolledCourseCardController {
    @FXML
    private Label instructor;

    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label totalStudent;

    public void setData(CourseInfo c){
        instructor.setText(c.instructorName);
        title.setText(c.title);
        id.setText(c._id);
    }
}
