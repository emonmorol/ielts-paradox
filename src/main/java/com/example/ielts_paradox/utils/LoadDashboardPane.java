package com.example.ielts_paradox.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

public class LoadDashboardPane {
    private AnchorPane sidePane;
    public AnchorPane getSidePane(String fileName){
        try{
            URL fileUrl = getClass().getResource(fileName);
            sidePane = new FXMLLoader().load(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sidePane;
    }
}
