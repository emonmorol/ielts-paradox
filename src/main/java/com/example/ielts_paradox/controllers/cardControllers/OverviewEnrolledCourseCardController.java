package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.CourseContentController;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.models.CourseInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class OverviewEnrolledCourseCardController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    @FXML
    public void detailsHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/courseContent.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        CourseContentController ccc = fxmlLoader.getController();
        CourseInfo ci = new ForCourse().getCourseById(id.getText());
        ccc.setData(ci,"1",ccc);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
