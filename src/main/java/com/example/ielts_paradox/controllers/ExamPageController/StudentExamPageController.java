package com.example.ielts_paradox.controllers.ExamPageController;

import com.example.ielts_paradox.Alerts.SeeResultAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class StudentExamPageController {


    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;
    @FXML
    private Label points;

    @FXML
    private Hyperlink meetId;

    @FXML
    private TextField answerArea;


    @FXML
    private Hyperlink practiseId;

    @FXML
    private Hyperlink questionId;

    @FXML
    private MFXButton resultId;

    @FXML
    void answerSubmit(ActionEvent event)  {
        String text = answerArea.getText();
        SuccessAlert.displayCustomAlert();

        System.out.println("Text from TextArea: " + text);

    }



    @FXML
    void downloadQuestion(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1OEipG7fBV-N3csrsQwfEbSGNiQsw33x6/view?usp=sharing"));

    }

    @FXML
    void meetLink(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1OEipG7fBV-N3csrsQwfEbSGNiQsw33x6/view?usp=sharing"));

    }

    @FXML
    void practiseQuestion(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1OEipG7fBV-N3csrsQwfEbSGNiQsw33x6/view?usp=sharing"));

    }

    @FXML
    void resultLink(ActionEvent event) {
        SeeResultAlert.displayCustomAlert("Your result: ","9","https://drive.google.com/file/d/1kHw1xy011UjnmJ-fcqTGVw8Wj1nH_14w/view?usp=sharing");

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
    public void setData(String point){
        points.setText(point);
    }


}
