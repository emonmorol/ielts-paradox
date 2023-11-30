package com.example.ielts_paradox.controllers.alertController;

import com.example.ielts_paradox.controllers.AllControl.FullBlogController;
import com.example.ielts_paradox.database.ForBlogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExamAlertController {

    @FXML
    private Stage dialogStage;
    @FXML
    private  Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }


    @FXML
    void closeAlert(ActionEvent event) {
        dialogStage.close();

    }

    @FXML
    void goToCheckoutPage(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/checkout.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }


}
