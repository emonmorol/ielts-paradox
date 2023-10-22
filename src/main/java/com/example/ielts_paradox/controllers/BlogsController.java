package com.example.ielts_paradox.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BlogsController implements Initializable {

    @FXML
    private VBox recentBlogs,first,second;
    ArrayList<String> it = new ArrayList<>();
    ArrayList<String> it2 = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");
        it2.add("aksjhdsf");


        for(String i:it){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/students/cards/recentBlogCard.fxml"));
                AnchorPane paneee = fxmlLoader.load();
                recentBlogs.getChildren().add(paneee);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        boolean isFirst = true;
        for(String i:it){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/students/cards/blogCard.fxml"));
                VBox paneee = fxmlLoader.load();
                if(isFirst){
                    first.getChildren().add(paneee);
                    isFirst = false;
                }else{
                    second.getChildren().add(paneee);
                    isFirst= true;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
