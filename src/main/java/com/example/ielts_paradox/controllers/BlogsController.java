package com.example.ielts_paradox.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BlogsController implements Initializable {
    @FXML
    private BorderPane mainBlogPane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/blogs/allBlog.fxml"));
            ScrollPane paneee = fxmlLoader.load();
            mainBlogPane.setCenter(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void addBlogHandler(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/blogs/addBlog.fxml"));
            AnchorPane paneee = fxmlLoader.load();

            mainBlogPane.setCenter(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void allBlogHandler(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/blogs/allBlog.fxml"));
            ScrollPane paneee = fxmlLoader.load();
            mainBlogPane.setCenter(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void myBlogHandler(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/blogs/myBlog.fxml"));
            AnchorPane paneee = fxmlLoader.load();
            mainBlogPane.setCenter(paneee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
