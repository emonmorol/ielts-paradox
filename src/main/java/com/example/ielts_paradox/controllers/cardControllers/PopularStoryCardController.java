package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.AllControl.FullBlogController;
import com.example.ielts_paradox.controllers.AllControl.FullStoryController;
import com.example.ielts_paradox.database.ForBlogs;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.StoryInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PopularStoryCardController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label band;

    @FXML
    private Label studentName;
    @FXML
    private Label id;

    public void setData(StoryInfo bi){
        studentName.setText(bi.studentName);
        band.setText("Band Score: "+bi.bandScore);
        id.setText(bi._id);
    }
    public void detailsHandler(ActionEvent event) throws IOException {
        String _id = id.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/full_success_story.fxml"));
        root = fxmlLoader.load();
        FullStoryController fsc = fxmlLoader.getController();
        fsc.setData(new ForStories().getStoryById(id.getText()),"1");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
