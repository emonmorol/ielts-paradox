package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.AllControl.FullBlogController;
import com.example.ielts_paradox.controllers.AllControl.TeacherCourseRequestController;
import com.example.ielts_paradox.models.BlogInfo;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherMyCourseCardController {



    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private MFXButton detailsBlogHandler;

    @FXML
    private Label publisher;

    @FXML
    private Label title;

    @FXML
    private Label id;

//    public void setData(BlogInfo bi){
//        publisher.setText(bi.publisherName);
//        title.setText(bi.title);
//        id.setText(bi._id);
//    }
    public void goToReqTable(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/pages/courses/teacherCourseRequest.fxml"));
        root = fxmlLoader.load();
        TeacherCourseRequestController tcrt = fxmlLoader.getController();
        tcrt.setData("0");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



}
