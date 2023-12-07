package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.controllers.AllControl.FullStoryController;
import com.example.ielts_paradox.controllers.SuccessStoriesController;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.StoryInfo;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StoryCardController {
//    public static StoryInfo story;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label bandScore;

    @FXML
    private Label mainStory;

    @FXML
    private Label studentName;

    @FXML
    private Label id;

    @FXML
    private Circle circle;

    @FXML
    public void viewFullStory(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/full_success_story.fxml"));
            root = fxmlLoader.load();
            FullStoryController fsc = fxmlLoader.getController();
            fsc.setData(new ForStories().getStoryById(id.getText()),"0");
            scene = new Scene(root);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
    public void setData(StoryInfo story){
        System.out.println("from set data "+story);
        id.setText(story._id);
        bandScore.setText(story.bandScore);
        mainStory.setText(story.mainStory);
        studentName.setText(story.studentName);

        if (story.studentImage != null && story.studentImage.length()!=0) {
            File imageFile = new File(story.studentImage);
            try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
                Image img = new Image(fileInputStream,300, 300, false,true);
                circle.setFill(new ImagePattern(img));
            } catch (FileNotFoundException e) {
                ErrorAlert.displayCustomAlert("Error Loading", "Image File Not Found: " + story.studentImage);
            } catch (IOException e) {
                ErrorAlert.displayCustomAlert("Error Loading", "Error reading image file: " + e.getMessage());
            }
        } else {
            File imageFile = new File("universal.jpg");
            try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
                Image img = new Image(fileInputStream,300, 300, false,true);
                circle.setFill(new ImagePattern(img));
            } catch (FileNotFoundException e) {
                ErrorAlert.displayCustomAlert("Error Loading", "Image File Not Found: " + "universal.jpg");
            } catch (IOException e) {
                ErrorAlert.displayCustomAlert("Error Loading", "Error reading image file: " + e.getMessage());
            }
        }

    }
}
