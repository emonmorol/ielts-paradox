package com.example.ielts_paradox.controllers.cardControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentRequestCardController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void goToCourseReqPage(ActionEvent event) throws IOException {
        //        String _id = id_.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/pages/courses/teacherCourseRequest.fxml"));
        root = fxmlLoader.load();
//        FullBlogController fbc = fxmlLoader.getController();
//        fbc.setData(new ForBlogs().getBlogById(_id) , "1");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    }



