package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.Alerts.Alert2;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable{
    @FXML
    private Label bio;

    @FXML
    private MFXButton bioEditor;

    @FXML
    private Label contact;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label password;

    @FXML
    private MFXButton profilePicEditor;

    @FXML
    private Label topName;

    @FXML
    private Label userType;

    @FXML
    public void bioEditor(ActionEvent event) {

    }

    @FXML
    public void contactEditor(ActionEvent event) {
        Alert2.displayCustomAlert("Contact");
        setData();
    }

    @FXML
    public void emailEditor(ActionEvent event) {
        Alert2.displayCustomAlert("Email");
        setData();
    }

    @FXML
    public void nameEditor(ActionEvent event) {
        Alert2.displayCustomAlert("Full Name");
        setData();
    }

    @FXML
    public void passwordEditor(ActionEvent event) {
        Alert2.displayCustomAlert("Password");
        setData();
    }

    @FXML
    public void profilePicEditor(ActionEvent event) {

    }
    public void setData(){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        topName.setText(info.fullName);
        if(info.isTeacher){
            userType.setText("Teacher");
        }else{
            userType.setText("Student");
        }
        email.setText(info.email);
        name.setText(info.fullName);
        contact.setText(info.contactNumber);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
