package com.example.ielts_paradox.controllers.CourseDetails;

import com.example.ielts_paradox.models.Faq;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.io.IOException;
import java.util.ArrayList;

public class FaqController {
    @FXML
    private Accordion accordion;

    public void setData(ArrayList<Faq> faqs) throws IOException {
        for(Faq faq: faqs){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/students/courseDetails/titledPanCard.fxml"));
            TitledPane tp = loader.load();
            TitledPanController tpc = loader.getController();
            tpc.setData(faq);
            accordion.getPanes().add(tp);
        }
    }
}
