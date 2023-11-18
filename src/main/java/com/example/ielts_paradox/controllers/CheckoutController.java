package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.PaidStudentInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.Alert1;
import com.example.ielts_paradox.utils.DBConnections;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckoutController {
    @FXML
    public Label courseTitle;

    @FXML
    private MFXTextField studentEmailField;
    @FXML
    private MFXTextField courseTitleField;
    @FXML
    private MFXTextField bkashNumber;

    @FXML
    private MFXTextField transectionId;

    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;
    public static CourseInfo ci ;
    @FXML
    public void backButtonHandler(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/offeredCourseDetails.fxml"));
        root = fxmlLoader.load();
        CourseDetailsController cdc = fxmlLoader.getController();
        cdc.setDetailsInfo(ci);
        scene = new Scene(root);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void setData(CourseInfo cf){
        ci = cf;
        UserSingleTon ins = UserSingleTon.getInstance(new UserInfo());
        UserInfo user = ins.getUser();
        System.out.println(user.email);
        System.out.println(user.fullName);
        studentEmailField.setText(user.email);
        courseTitleField.setText(ci.title);
        courseTitle.setText(ci.title);
    }
    @FXML
    public void enrollmentHandler(ActionEvent event){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        PaidStudentInfo psi = new PaidStudentInfo(bkashNumber.getText(),transectionId.getText(),studentEmailField.getText(),Integer.parseInt(CheckoutController.ci._id),formattedDateTime,false,false);
        boolean isComplete = new ForEnrollment().courseEnrollment(psi);
        if(isComplete){
            Alert1.displayCustomAlert("Successfull","Wait for Approval!");
        }
        else{
            Alert1.displayCustomAlert("Error","Fill up the form with appropriete info!");
        }
    }
}
