package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.CourseDetailsController;
import com.example.ielts_paradox.controllers.CourseOfferingController;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseOfferingCardController{
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label _id;

    @FXML
    private Label courseDetails;

    @FXML
    private Label courseTitle;

    @FXML
    private Label discountedPrice;

    @FXML
    private Label instructorName;

    @FXML
    private Label totalPrice;

    @FXML
    void seeDetailsHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/offeredCourseDetails.fxml"));
        root = fxmlLoader.load();
        CourseDetailsController cdc = fxmlLoader.getController();
        CourseInfo cf = new DBConnections().getCourseById(_id.getText());
        cdc.setDetailsInfo(cf);
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void setData(CourseInfo course){
        courseDetails.setText(course.details);
        courseTitle.setText(course.title);
        totalPrice.setText(String.valueOf(course.price)+"TK");
        int disPrice = course.price - (course.price * (course.discount)/100);
        discountedPrice.setText(String.valueOf(disPrice)+"TK");
        instructorName.setText(course.instructorName);
        _id.setText(course._id);
    }

}
