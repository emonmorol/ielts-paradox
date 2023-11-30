package com.example.ielts_paradox.controllers.MockTestController;

import com.example.ielts_paradox.models.TestInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MockTestTableCardController {
    @FXML
    private Label email;

    @FXML
    private Label examDate;

    @FXML
    private Label examType;

    @FXML
    private Label paymentDate;

    @FXML
    private Label id_;

    @FXML
    void takeExamHandler(ActionEvent event) {

    }
    public void setData(TestInfo ti){
        id_.setText(ti._id);
        email.setText(ti.studentMail);
        examDate.setText(ti.examDate);
        examType.setText(ti.examModule);
        paymentDate.setText(ti.enrollmentDate);
    }
}
