package com.example.ielts_paradox.controllers.AllControl;

import com.example.ielts_paradox.models.PaidStudentInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProvidedCourseController implements Initializable {

    @FXML
    private VBox mainBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo u = user.getUser();
        ArrayList<PaidStudentInfo> it = new DBConnections().courseEnrollmentUsingEmail(u.email);
        for(PaidStudentInfo i:it){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/courseCard.fxml"));
                HBox paneee = fxmlLoader.load();
                mainBox.getChildren().add(paneee);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
