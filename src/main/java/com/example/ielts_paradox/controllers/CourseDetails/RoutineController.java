package com.example.ielts_paradox.controllers.CourseDetails;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoutineController {

    @FXML
    private ImageView routineImage;

    public void setData(String path){
        Image img = new Image(getClass().getResourceAsStream(path));
        routineImage.setImage(img);
    }
}
