package com.example.ielts_paradox.controllers.teacher;

import com.example.ielts_paradox.controllers.cardControllers.CourseCardHandler;
import com.example.ielts_paradox.controllers.cardControllers.TeacherMyCourseCardController;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherCourseController  implements Initializable {

    @FXML
    private VBox myCourseTable;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMyCourseTable();
    }
    @FXML
    void reloadPage(ActionEvent event) {
        setMyCourseTable();
    }

    public void setMyCourseTable(){
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo u = user.getUser();
        myCourseTable.getChildren().clear();
        ArrayList<CourseInfo> cis = new ForCourse().teacherCourses(u.email,100);

        for(CourseInfo ci : cis){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/teacherMyCourseCard.fxml"));
                HBox paneee = fxmlLoader.load();
                TeacherMyCourseCardController tmccc = fxmlLoader.getController();
                tmccc.setData(ci);
                myCourseTable.getChildren().add(paneee);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
