package com.example.ielts_paradox.controllers.AllControl;

import com.example.ielts_paradox.controllers.StudentDashboardController;
import com.example.ielts_paradox.controllers.TeacherDashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FullBlogController implements Initializable {

    @FXML
    private VBox allBlogBox;
    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;
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
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/blogCard.fxml"));
                VBox paneee = fxmlLoader.load();
                allBlogBox.getChildren().add(paneee);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    @FXML
    void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
        root = fxmlLoader.load();
        StudentDashboardController tdc =fxmlLoader.getController();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        tdc.onClick7(event);
        stage.show();
    }

}
