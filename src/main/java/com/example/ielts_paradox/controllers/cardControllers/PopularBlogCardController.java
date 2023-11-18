package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.models.BlogInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PopularBlogCardController {

    @FXML
    private Label publisher;

    @FXML
    private Label title;
    @FXML
    private Label id;

    public void setData(BlogInfo bi){
        publisher.setText(bi.publisherName);
        title.setText(bi.title);
        id.setText(bi._id);
    }
}
