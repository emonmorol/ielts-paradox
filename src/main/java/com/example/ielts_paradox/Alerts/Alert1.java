package com.example.ielts_paradox.utils;

import com.example.ielts_paradox.controllers.alertController.Alert1Controller;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Alert1 {
    private static Stage ownerStage;


    public static void displayCustomAlert( String title, String message) {

        try {
            FXMLLoader loader = new FXMLLoader(Alert1.class.getResource("/fxmls/alerts/alert1.fxml"));
            Parent root = loader.load();

            Alert1Controller controller = loader.getController();

            Stage customAlertStage = new Stage(StageStyle.UNDECORATED);

            customAlertStage.initModality(Modality.APPLICATION_MODAL);
            customAlertStage.initOwner(ownerStage);

            customAlertStage.setScene(new Scene(root));
            controller.setDialogStage(customAlertStage);
            addOpenAnimation(customAlertStage);

           controller.setMessage(message);
            controller.setTitle(title);
            customAlertStage.setTitle(title);
            customAlertStage.showAndWait();


            addCloseAnimation(customAlertStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addOpenAnimation(Stage customAlertStage) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), customAlertStage.getScene().getRoot());
        translateTransition.setFromY(-100);
        translateTransition.setToY(0);
        translateTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), customAlertStage.getScene().getRoot());
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    private static void addCloseAnimation(Stage customAlertStage) {
        // You can add a similar animation for closing the alert
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), customAlertStage.getScene().getRoot());
        translateTransition.setFromY(-100);
        translateTransition.setToY(0);
        translateTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), customAlertStage.getScene().getRoot());
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}
