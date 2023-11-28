package com.example.ielts_paradox.controllers.teacher;

import com.example.ielts_paradox.controllers.cardControllers.MyBlogsCardController;
import com.example.ielts_paradox.controllers.cardControllers.MyStoryCardController;
import com.example.ielts_paradox.database.ForBlogs;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.StoryInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyBlogController implements Initializable {

    @FXML
    private VBox myBlogTable;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMyBlogTable();
    }
    public void setMyBlogTable(){
        ArrayList<BlogInfo> asi = new ForBlogs().getMyBlogs();
        myBlogTable.getChildren().clear();
        for (BlogInfo si:asi){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/blogs/myBlogsCard.fxml"));
            try {
                HBox paneee = fxmlLoader.load();
                MyBlogsCardController mbcc = fxmlLoader.getController();
                mbcc.setDate(si);
                myBlogTable.getChildren().add(paneee);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reloadPage(javafx.event.ActionEvent event) {
        setMyBlogTable();
    }
}