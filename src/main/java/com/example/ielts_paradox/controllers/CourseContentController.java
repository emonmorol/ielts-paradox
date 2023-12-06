package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.controllers.cardControllers.CourseContentCardController;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.database.ForCourseContent;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.CourseVideo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
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
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseContentController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane bottomAnchor;

    @FXML
    private VBox contentSideBox;

    @FXML
    private WebView courseVideo;

    @FXML
    private Label id;

    @FXML
    private Label instructor;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Label point;

    @FXML
    private AnchorPane sideAnchor;

    @FXML
    private Label title;

    @FXML
    private AnchorPane topAnchor;

    @FXML
    private MFXProgressBar topProgress;

    @FXML
    private Label totalChapters;

    @FXML
    private AnchorPane webAnchor;


    public void loadVideo(String uri){
        courseVideo.getEngine().load(uri);
    }

    public void backButtonHandler(ActionEvent e) throws IOException {
        UserSingleTon ins = UserSingleTon.getInstance(new UserInfo());
        UserInfo user = ins.getUser();

        if(user.isTeacher){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/teacherDashboard.fxml"));
            root = fxmlLoader.load();
            TeacherDashboardController tdc =fxmlLoader.getController();
            if(point.getText()=="0"){
                tdc.onClickOne(e);
            }else{
                tdc.onClickOverview(e);
            }
            scene = new Scene(root);
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);

        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc = fxmlLoader.getController();
            if(point.getText()=="0"){
                sdc.onClickOne(e);
            }else{
                sdc.onClickOverview(e);
            }
            scene = new Scene(root);
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }
        stage.show();
    }

    @FXML
    void fullScreen(ActionEvent event) {

    }
    @FXML
    void meterials(ActionEvent event) {

    }
    @FXML
    void next(ActionEvent event) {

    }
    @FXML
    void previous(ActionEvent event) {

    }

    CourseContentController ccc;
    ArrayList<CourseVideo> cvs;

    public void setData(CourseInfo ci,String p,CourseContentController oldCCC){
        ccc = oldCCC;
        point.setText(p);

        try{
            id.setText(ci._id);
            title.setText(ci.title);
            instructor.setText(ci.instructorName + " ,IELTS Instructor");
        }catch (Exception e){
            e.printStackTrace();
        }
        String id_ = id.getText();
        cvs = new ForCourseContent().getCourseContent(Integer.parseInt(id_));
        totalChapters.setText("Total "+cvs.size()+" Chapters");

        int lastWatchedId = -1;

        for(CourseVideo cv : cvs){
            if(!cv.isWatched){
                lastWatchedId = cv._id - 2;
                break;
            }
        }

        if(lastWatchedId == -1){
            loadVideo(cvs.get(cvs.size()-1).videoURI);
        }else{
            loadVideo(cvs.get(lastWatchedId).videoURI);
        }

        for(CourseVideo i:cvs){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/courseContentCard.fxml"));
                AnchorPane paneee = fxmlLoader.load();
                CourseContentCardController cccc = fxmlLoader.getController();
                cccc.setData(ci,p,oldCCC,i);

                contentSideBox.getChildren().add(paneee);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public boolean updateVideoWatching(int couserId,int videoId){
        cvs.get(videoId-1).isWatched = true;
        Gson gson = new Gson();

        JsonArray jsonArray = new JsonArray();
        for (CourseVideo course : cvs) {
            JsonElement jsonElement = gson.toJsonTree(course);
            jsonArray.add(jsonElement);
        }
        String jsonString = gson.toJson(jsonArray);
        return (new ForCourseContent().updateWatch(jsonString,couserId));
    }

    public boolean validate(int videoId){
        if(videoId==1 || cvs.get(videoId-2).isWatched){
            return true;
        }
        return false;
    }

}
