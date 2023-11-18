package com.example.ielts_paradox.controllers.teacher;

import com.example.ielts_paradox.utils.LoadDashboardPane;
import com.example.ielts_paradox.utils.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherDashboardController  implements Initializable {
    @FXML
    BorderPane mainPane = new BorderPane();
    @FXML
    Rectangle clip;
    @FXML
    ImageView img;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/overview.fxml");
        mainPane.setCenter(panel);
    }
    @FXML
    public void onClickOne(ActionEvent e){
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/my_classes.fxml");
        mainPane.setCenter(panel);
    }
    @FXML
    public void onClick2(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/courses.fxml");
        mainPane.setCenter(panel);
    }

    @FXML
    public void onClick3(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/success_stories.fxml");
        mainPane.setCenter(panel);
    }

    @FXML
    public void onClick4(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/blogs.fxml");
        mainPane.setCenter(panel);
    }

    @FXML
    public void onClick5(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/mock_test.fxml");
        mainPane.setCenter(panel);
    }

    @FXML
    public void onClick6(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/overview.fxml");
        mainPane.setCenter(panel);
    }

    @FXML
    public void onClick7(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/blogs.fxml");
        mainPane.setCenter(panel);
    }

    @FXML
    public void onClickOverview(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/overview.fxml");
        mainPane.setCenter(panel);
    }
    @FXML
    public void profileHandler(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/profile.fxml");
        mainPane.setCenter(panel);
    }
    public void logout(ActionEvent e) throws IOException {
        new SceneChanger().switchScene(e,"/fxmls/login/login_page.fxml");
    }


}