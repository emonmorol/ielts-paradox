package com.example.ielts_paradox.controllers.CourseDetails;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RoutineController {

    @FXML
    private ImageView routineImage;

    public void setData(String path){
        if (path != null) {
            File imageFile = new File(path);
            try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
                Image img = new Image(fileInputStream);
                routineImage.setImage(img);
            } catch (FileNotFoundException e) {
                ErrorAlert.displayCustomAlert("Error Loading", "Image File Not Found: " + path);
            } catch (IOException e) {
                ErrorAlert.displayCustomAlert("Error Loading", "Error reading image file: " + e.getMessage());
            }
        } else {
            ErrorAlert.displayCustomAlert("Error Loading", "Image Path is NULL");
        }
    }
}
