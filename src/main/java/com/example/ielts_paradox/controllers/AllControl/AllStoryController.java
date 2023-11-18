package com.example.ielts_paradox.controllers.AllControl;

import com.example.ielts_paradox.controllers.cardControllers.StoryCardController;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.StoryInfo;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllStoryController implements Initializable {
    @FXML
    private VBox firstBox;

    @FXML
    private VBox secondBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<StoryInfo> stories = new ForStories().getAllStories(0,100);
        boolean isFirst = true;
        for(StoryInfo story:stories){
            try {
                System.out.println("from loop = "+story._id);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/stories_card.fxml"));
                AnchorPane paneee = fxmlLoader.load();
                StoryCardController scc = fxmlLoader.getController();
                scc.setData(story);

                if(isFirst){
                    firstBox.getChildren().add(paneee);
                    isFirst = false;
                }
                else{
                    secondBox.getChildren().add(paneee);
                    isFirst= true;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
