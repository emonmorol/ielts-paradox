package com.example.ielts_paradox.controllers.FormControllers.courseForm;

import com.example.ielts_paradox.models.CourseVideo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.base.MFXLabeled;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class CourseForm2 {


    @FXML
    private FlowPane addInFlowPane;

    @FXML
    private MFXTextField curriculum;


    @FXML
    private MFXScrollPane sPane;

    private ArrayList<String> curriculumList = new ArrayList<>();

    @FXML
    void addCurriculum(ActionEvent event) {
        String curriculumText = curriculum.getText().trim();
        if (!curriculumText.isEmpty()) {
            curriculumList.add(curriculumText);


            Label curriculumItem = new Label(curriculumText);
            curriculumItem.setPrefWidth(200);
            curriculumItem.setPrefHeight(67.0);
            curriculumItem.setWrapText(true);
            curriculumItem.setAlignment(Pos.TOP_LEFT);
            curriculumItem.setStyle("-fx-border-color:#000000;-fx-border-width:1px;-fx-padding:10px;-fx-border-radius:5px");

            addInFlowPane.getChildren().add(curriculumItem);
            sPane.setVvalue(1.0);

            curriculum.clear();
        }
    }

    @FXML
    void saveButton(ActionEvent event) {

        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        for (String course : curriculumList) {
            jsonArray.add(gson.toJsonTree(course));
        }

        String jsonString = jsonArray.toString();
        System.out.println(jsonString);
    }
}
