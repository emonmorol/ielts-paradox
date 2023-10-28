package com.example.ielts_paradox.controllers;


import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.utils.AlertClass;
import com.example.ielts_paradox.utils.DBConnections;
import com.example.ielts_paradox.utils.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    @FXML
    private RadioButton student;

    @FXML
    private RadioButton teacher;

    @FXML
    private TextField userContactNumber;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;


    @FXML
    private PasswordField user_confirm_password;
    boolean isTeacher = false;
    @FXML
    public void getUserType(ActionEvent e){
        if(student.isSelected()) isTeacher = false;
        if(teacher.isSelected()) isTeacher = true;
    }
    public void switchToLogin(ActionEvent e) throws IOException {
        new SceneChanger().switchScene(e,"/fxmls/login/login_page.fxml");
    }
    @FXML
    public void registerPressed(ActionEvent e) throws IOException {

        String email = userEmail.getText();
        String pass = userPassword.getText();
        String fullname = userName.getText();
        String contact_number = userContactNumber.getText();
        String confirmPass = user_confirm_password.getText();

        if(fullname==""){
            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Full Name!");
        }
        else if(email==""){
            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Email!");
        }

        else if(contact_number==""){
            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Contact Number!");
        }
        else if(pass==""){
            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Password!");
        }
        else if(confirmPass==""){
            AlertClass.showAlert("ERROR!!!!!","Please Confirm Your Password!");
        }
        else if(!pass.equals(confirmPass)){
            AlertClass.showAlert("ERROR!!!!!","Password Didn't Match!");
        }
        else{
            UserInfo u = new UserInfo(fullname,email,contact_number,pass,isTeacher);
            boolean isValid = new DBConnections().isUserExist(email);
            if(isValid){
                AlertClass.showAlert("ERROR!!!!!","User Already Exits! Please Login!");
            }else if(!isValid){
                boolean isCreated = new DBConnections().createUser(u);
                if(isCreated){
                    new SceneChanger().switchScene(e, "/fxmls/login/login_page.fxml");
//                    if(isTeacher){
//                        new SceneChanger().switchScene(e, "/fxmls/teacher/teacherDashboard.fxml");
//                    }else{
//                        new SceneChanger().switchScene(e, "/fxmls/students/studentDashboard.fxml");
//                    }
                }else {
                    AlertClass.showAlert("ERROR!!!!!","Something Went Wrong\nTry Again!");
                }
            }else{
                AlertClass.showAlert("ERROR!!!!!","Something Went Wrong\nTry Again!");
            }
        }

    }
}
