package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.SuccessStoriesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class StoryCardController {
    @FXML
    public void viewFullStory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/teacher/pages/success_stories.fxml"));
        Parent root = loader.load();
        SuccessStoriesController ac = loader.getController();

//        System.out.println(ac.mainPane);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/fullStory.fxml"));
        VBox paneee = fxmlLoader.load();
        System.out.println(ac.anchor);
        ac.secondBox.getChildren().add(paneee);
//        ac.mainPane.layout();
//        BorderPane nestedParent = (BorderPane) root.lookup("#mainPane");
//        BorderPane main = (BorderPane) root.lookup("#mainPane");
//        if (main == null) {
//
//            System.out.println(nestedParent);
//            if (nestedParent != null) {
//                main = (BorderPane) nestedParent.lookup("#mainPane");
//            }
//        }
//        System.out.println(nestedParent);
//        ac.sceneChange(event,"/fxmls/teacher/pages/fullStory.fxml");
//        nestedParent.setCenter(paneee);
    }
}
