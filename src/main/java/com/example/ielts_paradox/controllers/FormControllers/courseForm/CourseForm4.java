package com.example.ielts_paradox.controllers.FormControllers.courseForm;

import com.example.ielts_paradox.models.CourseVideo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class CourseForm4 {

    ArrayList<CourseVideo> cvs = new ArrayList<>();

    int idCount =0;
    @FXML
    private MFXTextField title;

    @FXML
    private TextArea uri;

    @FXML
    void addNext(ActionEvent event) {
        idCount++;
        if(idCount==1){
            cvs.add(new CourseVideo(idCount,title.getText(),uri.getText(),true));

        }
        else{
            cvs.add(new CourseVideo(idCount,title.getText(),uri.getText(),false));
        }
        title.clear();
        uri.clear();

    }

    @FXML
    void saveButton(ActionEvent event) {
        Gson gson = new Gson();

        JsonArray jsonArray = new JsonArray();
        for (CourseVideo course : cvs) {
            JsonElement jsonElement = gson.toJsonTree(course);
            jsonArray.add(jsonElement);
        }

        String jsonString = gson.toJson(jsonArray);
        System.out.println(jsonString);

    }
}
