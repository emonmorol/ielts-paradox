package com.example.ielts_paradox.controllers.CourseDetails;

import com.example.ielts_paradox.models.Faq;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

import java.util.ArrayList;

public class TitledPanController {
    @FXML
    private Label answer;

    @FXML
    private TitledPane question;

    public void setData(Faq faq) {
        answer.setText(faq.answer);
        question.setText(faq.question);
    }
}
