package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.database.ForUser;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.utils.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private RadioButton teacher;
    @FXML
    private RadioButton student;
    boolean isTeacher = false;
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;

    @FXML
    public void getUserType(ActionEvent e){
        if(student.isSelected()) isTeacher = false;
        if(teacher.isSelected()) isTeacher = true;
    }
    public void switchToRegister(ActionEvent e) throws IOException {
        new SceneChanger().switchScene(e,"/fxmls/login/register_page.fxml");
    }
    @FXML
    public void loginPressed(ActionEvent e) throws IOException {
        UserInfo user;

        String email = userEmail.getText();
        String pass = userPassword.getText();

        if(email==""){
            ErrorAlert.displayCustomAlert("ERROR!","Please Enter Your Email Address!");
//            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Email Address!");
        }
        else if(pass==""){
            ErrorAlert.displayCustomAlert("ERROR!","Please Enter Your Password!");
//            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Password!");
        }else{
            boolean isValid = new ForUser().validate(email,pass,isTeacher);
            if(isValid){
                if(isTeacher){
                    new SceneChanger().switchScene(e, "/fxmls/teacher/teacherDashboard.fxml");
                }else{
                    new SceneChanger().switchScene(e, "/fxmls/students/studentDashboard.fxml");
                }
            }else if(!isValid){
                ErrorAlert.displayCustomAlert("ERROR!","Incorrect Credentials!\nPlease Enter Correct Information.");
//                AlertClass.showAlert("ERROR!!!!!","Incorrect Credentials!");
            }else{
                ErrorAlert.displayCustomAlert("ERROR!","Something Went Wrong\nTry Again!");
//                AlertClass.showAlert("ERROR!!!!!","Something Went Wrong\nTry Again!");
            }
        }

    }
}
