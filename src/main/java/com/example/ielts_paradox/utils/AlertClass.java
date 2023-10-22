package com.example.ielts_paradox.utils;

import javafx.scene.control.Alert;

public class AlertClass {
    public static void showAlert(String title,String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
}
