package com.example.ielts_paradox.controllers.AllControl;

import com.example.ielts_paradox.controllers.cardControllers.TeacherApprovedStudentsTableCardController;
import com.example.ielts_paradox.controllers.cardControllers.TeacherCourseRequestCardController;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.PaidStudentInfo;
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

public class TeacherApprovedStudentsTableController {
    public static CourseInfo ci;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label points;
    @FXML
    private VBox myCourseTable;

    @FXML
    private Label title;

    @FXML
    void reloadPage(ActionEvent event) throws IOException {
        setData(TeacherCourseRequestController.ci,points.getText());
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
                tdc.onClickOne(event);
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
                sdc.onClickOne(event);
            else
                sdc.onClickOverview(event);
        }
        stage.show();
    }

    public void setData(CourseInfo ci, String point) throws IOException {
        points.setText(point);
        title.setText(ci.title + " - "+"Approved Students");
        TeacherApprovedStudentsTableController.ci = ci;
        ArrayList<PaidStudentInfo> paidStudentInfos = new ForEnrollment().getRequests(ci._id,100,true);
        myCourseTable.getChildren().clear();
        for(PaidStudentInfo paidStudentInfo:paidStudentInfos){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/courses/teacherApprovedStudentsTableCard.fxml"));
            HBox paneee = fxmlLoader.load();
            TeacherApprovedStudentsTableCardController tastcc = fxmlLoader.getController();
            tastcc.setData(paidStudentInfo);
            myCourseTable.getChildren().add(paneee);
        }
    }


}
