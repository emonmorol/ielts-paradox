package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.controllers.cardControllers.CourseContentCardController;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.database.ForCourseContent;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.CourseVideo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.stage.StageStyle;

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

    @FXML
    private Label mfxLevel;

    @FXML
    private MFXButton prevButton;

    @FXML
    private  MFXButton nextButton;


    CourseContentController ccc;
    ArrayList<CourseVideo> cvs;
    CourseInfo cc;


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

        }
        else{
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
    public int runningVideoId;

    @FXML
    void fullScreen(ActionEvent event) {
        toggleFullscreen(courseVideo);
    }

    private void toggleFullscreen(WebView webView) {
        Stage fullscreenStage = new Stage();
        fullscreenStage.initStyle(StageStyle.UNDECORATED);

        WebView fullscreenWebView = new WebView();
        fullscreenWebView.getEngine().load(webView.getEngine().getLocation());

        Scene fullscreenScene = new Scene(fullscreenWebView);

        fullscreenStage.setScene(fullscreenScene);
        fullscreenStage.setFullScreen(true);
        fullscreenScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                fullscreenStage.close();
            }
        });
        fullscreenStage.show();
    }
    @FXML
    void meterials(ActionEvent event) {

    }
    @FXML
    void next(ActionEvent event) {
        int newId = runningVideoId + 1;
        if(!isExist(newId)){
            return;
        }
        if(cvs.get(newId-1).isWatched){
            ccc.loadVideo(cvs.get(newId-1).videoURI);
            ccc.runningVideoId = newId;
            boolean isWatched = ccc.updateVideoWatching(Integer.parseInt(id.getText()), newId);

        }
        else if(ccc.validate(newId)){
            ccc.loadVideo(cvs.get(newId - 1).videoURI);
            ccc.runningVideoId = newId;
            boolean isWatched = ccc.updateVideoWatching(Integer.parseInt(id.getText()), newId);
            setData(cc,point.getText(),ccc);
        }
    }
    @FXML
    void previous(ActionEvent event) {
        int newId = runningVideoId - 1;
        if(!isExist(newId)){
            return;
        }
        if(cvs.get(newId-1).isWatched){
            ccc.loadVideo(cvs.get(newId-1).videoURI);
            ccc.runningVideoId = newId;
            boolean isWatched = ccc.updateVideoWatching(Integer.parseInt(id.getText()), newId);

        }
        else if(ccc.validate(newId)){
            ccc.loadVideo(cvs.get(newId - 1).videoURI);
            ccc.runningVideoId = newId;
            boolean isWatched = ccc.updateVideoWatching(Integer.parseInt(id.getText()), newId);
            setData(cc,point.getText(),ccc);
        }

    }



    public void setData(CourseInfo ci,String p,CourseContentController oldCCC){
        ccc = oldCCC;
        point.setText(p);
        cc = ci;

        try{
            id.setText(ci._id);
            title.setText(ci.title);
            instructor.setText(ci.instructorName + " ,IELTS Instructor");
        }catch (Exception e){
            e.printStackTrace();
        }
        String id_ = id.getText();
        cvs = new ForCourseContent().getCourseContent(Integer.parseInt(id_));
        totalChapters.setText("TOTAL CHAPTERS "+cvs.size());

        int lastWatchedId = -1,watchCount = 0;

        for(CourseVideo cv : cvs){

            if(!cv.isWatched){
                lastWatchedId = cv._id - 2;
                break;
            }
            watchCount++;
        }

        double prog = (((double) watchCount/cvs.size()));
        mfxLevel.setText((int)(prog*100)+"%");
        topProgress.setProgress(prog);

        if(lastWatchedId == -1){
            runningVideoId = cvs.size();
            loadVideo(cvs.get(cvs.size()-1).videoURI);
        }else{
            runningVideoId = lastWatchedId + 1;
            loadVideo(cvs.get(lastWatchedId).videoURI);
        }
        contentSideBox.getChildren().clear();
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

        int lastWatchedId = -1,watchCount = 0;

        for(CourseVideo cv : cvs){

            if(!cv.isWatched){
                lastWatchedId = cv._id - 2;
                break;
            }
            watchCount++;
        }
        double prog = (((double) watchCount/cvs.size()));
        mfxLevel.setText((int)(prog*100)+"%");
        topProgress.setProgress(prog);


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

    public boolean isExist(int videoId){
        if(videoId<1 || videoId>cvs.size()) return false;
        return true;
    }

}
