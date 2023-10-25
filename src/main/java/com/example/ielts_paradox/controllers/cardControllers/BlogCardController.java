package com.example.ielts_paradox.controllers.cardControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BlogCardController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void viewFullBlog(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/pages/blogs/full_blogs.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
