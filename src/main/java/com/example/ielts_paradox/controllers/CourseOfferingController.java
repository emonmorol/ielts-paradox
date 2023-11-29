package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.controllers.cardControllers.CourseOfferingCardController;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.Faq;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseOfferingController implements Initializable {

    @FXML
    private VBox firstBox,secondBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<CourseInfo> courses = new ForCourse().getAllCourse(0,100);

        boolean isFirst = true;
        for(CourseInfo i:courses){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/courseOfferingCard.fxml"));


                VBox paneee = fxmlLoader.load();
                CourseOfferingCardController cocd = fxmlLoader.getController();
                cocd.setData(i);
                if(isFirst){
                    firstBox.getChildren().add(paneee);
                    isFirst = false;
                }else{
                    secondBox.getChildren().add(paneee);
                    isFirst= true;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
