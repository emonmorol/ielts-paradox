package com.example.ielts_paradox.controllers.AllControl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FullBlogController implements Initializable {

    @FXML
    private VBox allBlogBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> it = new ArrayList<>();
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");

        for(String i:it){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/students/cards/blogCard.fxml"));
                VBox paneee = fxmlLoader.load();
                allBlogBox.getChildren().add(paneee);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
