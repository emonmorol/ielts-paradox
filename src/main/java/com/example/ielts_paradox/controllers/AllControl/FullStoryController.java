package com.example.ielts_paradox.controllers.AllControl;

import com.example.ielts_paradox.controllers.cardControllers.StoryCardController;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.StoryInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FullStoryController implements Initializable {

    @FXML
    private VBox allBlogBox;
    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;



    @FXML
    private Label bandScore;
    @FXML
    private Label points;

    @FXML
    private Label mainStory;

    @FXML
    private Label studentName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<StoryInfo> stories = new ForStories().getAllStories(0,100);
        for(StoryInfo story:stories){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/stories_card.fxml"));
                AnchorPane paneee = fxmlLoader.load();
                StoryCardController scc = fxmlLoader.getController();
                scc.setData(story);
                allBlogBox.getChildren().add(paneee);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    @FXML
    void backButtonHandler(ActionEvent event) throws IOException {
        UserSingleTon ins = UserSingleTon.getInstance(new UserInfo());
        UserInfo user = ins.getUser();
        if(user.isTeacher){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/teacherDashboard.fxml"));
            root = fxmlLoader.load();
            TeacherDashboardController tdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            if(points.getText()=="0")
                tdc.onClick3(event);
            else
                tdc.onClickOverview(event);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            if(points.getText()=="0")
                sdc.onClick3(event);
            else
                sdc.onClickOverview(event);
        }
        stage.show();
    }
    public void setData(StoryInfo story,String p){
        points.setText(p);
        bandScore.setText("Band Score: "+story.bandScore);
        mainStory.setText(story.mainStory);
        studentName.setText(story.studentName);
    }

}
