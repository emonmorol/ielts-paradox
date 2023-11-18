package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.AllControl.FullBlogController;
import com.example.ielts_paradox.database.ForBlogs;
import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BlogCardController {

    @FXML
    private Label content;

    @FXML
    private Label date;

    @FXML
    private Label id;

    @FXML
    private Label publisher;

    @FXML
    private Label title;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void viewFullBlog(ActionEvent event) throws IOException {
        String _id = id.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/pages/blogs/full_blogs.fxml"));
        root = fxmlLoader.load();
        FullBlogController fbc = fxmlLoader.getController();
        fbc.setData(new ForBlogs().getBlogById(_id));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void setData(BlogInfo blog){
        content.setText(blog.content);
        date.setText(blog.date);
        id.setText(blog._id);
        publisher.setText(blog.publisherName);
        title.setText(blog.title);
    }
}
