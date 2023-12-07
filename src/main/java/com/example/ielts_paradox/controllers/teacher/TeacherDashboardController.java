package com.example.ielts_paradox.controllers.teacher;

import com.example.ielts_paradox.controllers.cardControllers.LatestNoticeCardController;
import com.example.ielts_paradox.database.ForNotices;
import com.example.ielts_paradox.models.NoticeInfo;
import com.example.ielts_paradox.utils.LoadDashboardPane;
import com.example.ielts_paradox.utils.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class TeacherDashboardController  implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private VBox vBox;

    @FXML
    BorderPane mainPane = new BorderPane();
    @FXML
    private Label studentName;
    @FXML
    Rectangle clip;
    @FXML
    ImageView img;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInformation();
    }
    public void setInformation(){
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/teacher/pages/overview.fxml");
        mainPane.setCenter(panel);

        ArrayList<NoticeInfo> nis = new ForNotices().getNotices();
        Collections.sort(nis, Comparator.comparingInt(NoticeInfo::get_id).reversed());
        int cnt = 0;
        vBox.getChildren().clear();
        for(NoticeInfo ni : nis){
            if(cnt >=7){
                break;
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/latestNotice.fxml"));
            try {
                HBox paneee = fxmlLoader.load();
                vBox.getChildren().add(paneee);
                LatestNoticeCardController oeccc = fxmlLoader.getController();
                oeccc.setData(ni);
                cnt++;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void reloadHandler(ActionEvent event) {
        setInformation();
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
    public void reloadPage(ActionEvent e) throws IOException {
        Node currentCenterPane = mainPane.getCenter();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/teacher/teacherDashboard.fxml"));
        root = loader.load();
        TeacherDashboardController tdc = loader.getController();
        tdc.mainPane.setCenter(currentCenterPane);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
