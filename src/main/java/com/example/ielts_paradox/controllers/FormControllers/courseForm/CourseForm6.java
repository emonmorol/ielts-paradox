package com.example.ielts_paradox.controllers.FormControllers.courseForm;

import com.example.ielts_paradox.models.CourseVideo;
import com.example.ielts_paradox.models.Faq;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class CourseForm6 {

    @FXML
    private TextArea answer;

    @FXML
    private MFXTextField question;

    ArrayList<Faq> faqs = new ArrayList<>();



    @FXML
    void addNext(ActionEvent event) {
      faqs.add(new Faq(question.getText(),answer.getText()));

        question.clear();
        answer.clear();


    }

    @FXML
    void saveButton(ActionEvent event) {
        Gson gson = new Gson();

        JsonArray jsonArray = new JsonArray();
        for (Faq course : faqs) {
            JsonElement jsonElement = gson.toJsonTree(course);
            jsonArray.add(jsonElement);
        }

        String jsonString = gson.toJson(jsonArray);
        System.out.println(jsonString);

    }


}
