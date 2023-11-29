package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.AllControl.FullBlogController;
import com.example.ielts_paradox.controllers.AllControl.TeacherCourseRequestController;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.utils.LoadDashboardPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherOverviewMyCourseCardController {
    @FXML
    private Label instructor;

    @FXML
    private Label title;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label id_;


    public void setData(CourseInfo bi){
        instructor.setText(bi.instructorName);
        title.setText(bi.title);
        id_.setText(bi._id);
    }
    public void detailsHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/pages/courses/teacherCourseRequest.fxml"));
        root = fxmlLoader.load();
        TeacherCourseRequestController tcrt = fxmlLoader.getController();
        CourseInfo ci = new ForCourse().getCourseById(id_.getText());
        tcrt.setData(ci,"1");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }




}
