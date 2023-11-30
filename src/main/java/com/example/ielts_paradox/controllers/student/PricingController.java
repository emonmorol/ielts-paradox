package com.example.ielts_paradox.controllers.student;

import com.example.ielts_paradox.controllers.CheckoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PricingController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void listeningEnrollHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/checkout.fxml"));
        root = fxmlLoader.load();
        CheckoutController cc = fxmlLoader.getController();
        cc.setData("Listening","500");
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void readingEnrollHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/checkout.fxml"));
        root = fxmlLoader.load();
        CheckoutController cc = fxmlLoader.getController();
        cc.setData("Reading","600");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void speakingEnrollHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/checkout.fxml"));
        root = fxmlLoader.load();
        CheckoutController cc = fxmlLoader.getController();
        cc.setData("Speaking","700");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void writingEnrollHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/checkout.fxml"));
        root = fxmlLoader.load();
        CheckoutController cc = fxmlLoader.getController();
        cc.setData("Writing","800");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
