package com.example.ielts_paradox.controllers.MessagesController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class IncomingCard {
    @FXML
    private Label name;

    @FXML
    private TextFlow textFlow;


    public void setData(String msg){
//        name.setText(userName);
        Text t = new Text(msg);
        textFlow.getChildren().add(t);
    }
}
