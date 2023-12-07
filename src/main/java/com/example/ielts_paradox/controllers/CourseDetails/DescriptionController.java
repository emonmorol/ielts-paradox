package com.example.ielts_paradox.controllers.CourseDetails;
import com.example.ielts_paradox.models.CourseInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DescriptionController {
    @FXML
    private TitledPane courseTitle;

    @FXML
    private VBox curriculum;

    @FXML
    private Label description;

    @FXML
    private VBox features;

    @FXML
    private Label teacherName;

    @FXML
    private Label title;
    public void setData(CourseInfo ci) throws IOException {
        title.setText(ci.title);
        teacherName.setText(ci.instructorName);
        courseTitle.setText(ci.title);
        description.setText(ci.details);
        String[] curri = ci.curriculum;
        String[] featu = ci.features;
        features.getChildren().clear();
        for(String s:featu){
            FXMLLoader labelLoader = new FXMLLoader();
            labelLoader.setLocation(getClass().getResource("/fxmls/components/descriptionLabel.fxml"));
            Label pnt = labelLoader.load();
            pnt.setText(s);
            features.getChildren().add(pnt);
        }
        curriculum.getChildren().clear();
        for(String s:curri){
            FXMLLoader labelLoader = new FXMLLoader();
            labelLoader.setLocation(getClass().getResource("/fxmls/components/descriptionLabel.fxml"));
            Label pnt = labelLoader.load();
            pnt.setText(s);
            curriculum.getChildren().add(pnt);
        }
    }

}
