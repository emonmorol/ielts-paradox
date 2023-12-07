package com.example.ielts_paradox.controllers.FormControllers.courseForm;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class CourseForm5 {
    @FXML
    private MFXTextField sidebarPoint;
    @FXML
    private FlowPane addInFlowPane;
    @FXML
    private MFXScrollPane sPane;

    private ArrayList<String> sidebarPointArray = new ArrayList<>();

    @FXML
    void addSidebar(ActionEvent event) {
        String sideBarText = sidebarPoint.getText().trim();
        if (!sideBarText.isEmpty()) {
            sidebarPointArray.add(sideBarText);


            Label curriculumItem = new Label(sideBarText);
            curriculumItem.setPrefWidth(200);
            curriculumItem.setPrefHeight(67.0);
            curriculumItem.setWrapText(true);
            curriculumItem.setAlignment(Pos.CENTER);
            curriculumItem.setStyle("-fx-border-color:#000000;-fx-border-width:1px;-fx-padding:10px;-fx-border-radius:5px");
            addInFlowPane.getChildren().add(curriculumItem);
            sPane.setVvalue(1.0);
            sidebarPoint.clear();
        }
    }

    @FXML
    void saveButton(ActionEvent event) {
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        for (String course : sidebarPointArray) {
            jsonArray.add(gson.toJsonTree(course));
        }

        String jsonString = jsonArray.toString();
        AddCourseController.sidebarPoint = jsonString;
    }
}
