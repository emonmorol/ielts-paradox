package com.example.ielts_paradox.controllers.MockTestController;

import com.example.ielts_paradox.controllers.cardControllers.MyBlogsCardController;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.TestInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TestRequestController {

    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;
    @FXML
    private Label points;
    @FXML
    private VBox myCourseTable;
    @FXML
    private Label testType;

    @FXML
    void goToBackPage(ActionEvent event) throws IOException {
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
                tdc.onClick5(event);
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
                sdc.onClick5(event);
            else
                sdc.onClickOverview(event);
        }
        stage.show();
    }
    @FXML
    public void setData(String type,String point){
        points.setText(point);
        testType.setText(type);
        myCourseTable.getChildren().clear();
        ArrayList<TestInfo> tis = new ForTest().getTestRequests(type,100,false);

        for(TestInfo ti:tis){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/mocktest/testRequestTableCard.fxml"));
            try {
                HBox paneee = fxmlLoader.load();
                TestRequestCardController trcc = fxmlLoader.getController();
                trcc.setData(ti);
                myCourseTable.getChildren().add(paneee);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    void reloadPage(ActionEvent event) {
        setData(testType.getText(),points.getText());
    }
}
