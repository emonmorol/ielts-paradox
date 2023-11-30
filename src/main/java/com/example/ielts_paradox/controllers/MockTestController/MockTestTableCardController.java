package com.example.ielts_paradox.controllers.MockTestController;

import com.example.ielts_paradox.models.TestInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MockTestTableCardController {
    @FXML
    private Label email;

    @FXML
    private Label examDate;

    @FXML
    private Label examType;

    @FXML
    private Label paymentDate;

    @FXML
    private Label id_;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void takeExamHandler(ActionEvent event) throws IOException {
//        String id = id.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/teacher_exam_page.fxml"));
        root = fxmlLoader.load();
//        TeacherExamPageController fbc = fxmlLoader.getController();
//        fbc.setData(new ForBlogs().getBlogById(_id),"0");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void setData(TestInfo ti){
        id_.setText(ti._id);
        email.setText(ti.studentMail);
        examDate.setText(ti.examDate);
        examType.setText(ti.examModule);
        paymentDate.setText(ti.enrollmentDate);
    }
}
