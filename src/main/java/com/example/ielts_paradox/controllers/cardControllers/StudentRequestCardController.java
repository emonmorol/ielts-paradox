package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.AllControl.TeacherCourseRequestController;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.StudentRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentRequestCardController {
    @FXML
    private Label courseId;

    @FXML
    private Label courseTitle;

    @FXML
    private Label studentMail;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void goToCourseReqPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/pages/courses/teacherCourseRequest.fxml"));
        root = fxmlLoader.load();
        TeacherCourseRequestController tcrt = fxmlLoader.getController();
        CourseInfo ci = new ForCourse().getCourseById(courseId.getText());
        tcrt.setData(ci,"1");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void setData(StudentRequest sr){
        courseId.setText(sr._id);
        courseTitle.setText(sr.courseTitle);
        studentMail.setText(sr.studentMail);
    }

}



