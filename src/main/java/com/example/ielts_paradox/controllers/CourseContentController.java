package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseContentController implements Initializable {
    @FXML
    private VBox contentSideBox;

    @FXML
    private AnchorPane sideAnchor;
    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private AnchorPane topAnchor;

    @FXML
    private AnchorPane bottomAnchor;

    @FXML
    private WebView videoPlayer;

    @FXML
    private AnchorPane webAnchor;
    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;

    ArrayList<String> af = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        loadVideo("https://www.youtube.com/embed/jPB3odX-F9s");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        af.add("course");
        for(String i:af){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/courseContentCard.fxml"));
                AnchorPane paneee = fxmlLoader.load();
                contentSideBox.getChildren().add(paneee);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void loadVideo(String uri){
        videoPlayer.getEngine().load(uri);
    }
    public void backButtonHandler(ActionEvent e) throws IOException {
        UserSingleTon ins = UserSingleTon.getInstance(new UserInfo());
        UserInfo user = ins.getUser();
        System.out.println(user.isTeacher);
        System.out.println(user.fullName);
        System.out.println(user.email);
        System.out.println(user.contactNumber);
        if(user.isTeacher){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/teacherDashboard.fxml"));
            root = fxmlLoader.load();
            TeacherDashboardController tdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            tdc.onClickOne(e);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc =fxmlLoader.getController();
            scene = new Scene(root);
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            sdc.onClickOne(e);
        }
        stage.show();
    }

//    @FXML
//    void fullScreenHandler(ActionEvent event) {
//        Stage stage = (Stage) webAnchor.getScene().getWindow();
//
//        // Enter full-screen mode
//        stage.setFullScreen(true);
//
//        // Adjust WebView size to fill the screen
//        videoPlayer.setPrefSize(Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
//        videoPlayer.setLayoutX(-176);
//        videoPlayer.setLayoutY(-126);
//        sideAnchor.setVisible(false);
//        bottomAnchor.setVisible(false);
//        topAnchor.setVisible(false);
//    }
//    private void exitFullScreen() {
//        // Exit full-screen mode
//        stage.setFullScreen(false);
//
//        // Restore WebView size and layout
//        videoPlayer.setPrefSize(1500, 750);
//        videoPlayer.setLayoutX(0);
//        videoPlayer.setLayoutY(0);
//
//        // Restore visibility of other AnchorPane children
//        sideAnchor.setVisible(true);
//        bottomAnchor.setVisible(true);
//        topAnchor.setVisible(true);
//    }
}
