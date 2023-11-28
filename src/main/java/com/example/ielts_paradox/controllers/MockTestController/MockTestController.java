package com.example.ielts_paradox.controllers.MockTestController;

import com.example.ielts_paradox.controllers.AllControl.FullBlogController;
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

public class MockTestController {

    @FXML
    private Label pageTitle;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void goToListeningReq(ActionEvent event) throws IOException {
        //        String _id = id.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/listeningTestRequest.fxml"));
        root = fxmlLoader.load();
//        FullBlogController fbc = fxmlLoader.getController();
//        fbc.setData(new ForBlogs().getBlogById(_id) , "1");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void goToReadingReq(ActionEvent event) throws IOException {
        //        String _id = id.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/readingTestRequest.fxml"));
        root = fxmlLoader.load();
//        FullBlogController fbc = fxmlLoader.getController();
//        fbc.setData(new ForBlogs().getBlogById(_id) , "1");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void goToSpeakingReq(ActionEvent event) throws IOException {
        //        String _id = id.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/speakingTestRequest.fxml"));
        root = fxmlLoader.load();
//        FullBlogController fbc = fxmlLoader.getController();
//        fbc.setData(new ForBlogs().getBlogById(_id) , "1");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void goToWrittingReq(ActionEvent event) throws IOException {

        //        String _id = id.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/writingTestRequest.fxml"));
        root = fxmlLoader.load();
//        FullBlogController fbc = fxmlLoader.getController();
//        fbc.setData(new ForBlogs().getBlogById(_id) , "1");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }




}
