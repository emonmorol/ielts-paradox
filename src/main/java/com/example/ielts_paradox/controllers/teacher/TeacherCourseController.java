package com.example.ielts_paradox.controllers.teacher;

import com.example.ielts_paradox.controllers.cardControllers.CourseCardHandler;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
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
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo u = user.getUser();
        for(int i = 0;i<10;i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/teacherMyCourseCard.fxml"));
                HBox paneee = fxmlLoader.load();
                myCourseTable.getChildren().add(paneee);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
