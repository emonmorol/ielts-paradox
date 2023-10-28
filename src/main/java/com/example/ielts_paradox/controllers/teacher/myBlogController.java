package com.example.ielts_paradox.controllers.teacher;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class myBlogController  implements Initializable {

    @FXML
    private VBox myBlogTable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println();
        for (int i = 0; i<30;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/teacher/pages/blogs/myBlogsCard.fxml"));
            try {
                HBox paneee = fxmlLoader.load();
                myBlogTable.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}