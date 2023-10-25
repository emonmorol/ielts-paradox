package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.SuccessStoriesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StoryCardController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void viewFullStory(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/full_success_story.fxml"));
            root = fxmlLoader.load();
            scene = new Scene(root);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
}
