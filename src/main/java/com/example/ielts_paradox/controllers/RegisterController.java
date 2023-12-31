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
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private Stage stage;
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
            ErrorAlert.displayCustomAlert("ERROR!","Please Enter Your Full Name!");
//            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Full Name!");
        }
        else if(email==""){
            ErrorAlert.displayCustomAlert("ERROR!","Please Enter Your Email!");
//            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Email!");
        }

        else if(contact_number==""){
            ErrorAlert.displayCustomAlert("ERROR!","Please Enter Your Contact Number!");
//            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Contact Number!");
        }
        else if(pass==""){
            ErrorAlert.displayCustomAlert("ERROR!","Please Enter Your Password!");
//            AlertClass.showAlert("ERROR!!!!!","Please Enter Your Password!");
        }
        else if(confirmPass==""){
            ErrorAlert.displayCustomAlert("ERROR!","Please Enter Confirm Your Password");
//            AlertClass.showAlert("ERROR!!!!!","Please Confirm Your Password!");
        }
        else if(!pass.equals(confirmPass)){
            ErrorAlert.displayCustomAlert("ERROR!","Password Didn't Match!");
//            AlertClass.showAlert("ERROR!!!!!","Password Didn't Match!");
        }
        else{
            UserInfo u = new UserInfo(fullname,email,contact_number,pass,isTeacher,"We can’t express our gratitude enough for this teacher who always remembers to celebrate the little victories, not just the big ones.Can you imagine life without teachers?");
            boolean isValid = new ForUser().isUserExist(email);
            if(isValid){
                ErrorAlert.displayCustomAlert("ERROR!","User Already Exits! Please Login!");
//                AlertClass.showAlert("ERROR!!!!!","User Already Exits! Please Login!");
            }else if(!isValid){
                boolean isCreated = new ForUser().createUser(u);
                if(isCreated){
                    new SceneChanger().switchScene(e, "/fxmls/login/login_page.fxml");
                }else {
                    ErrorAlert.displayCustomAlert("ERROR!","Something Went Wrong\nTry Again!");
//                    AlertClass.showAlert("ERROR!!!!!","");
                }
            }else{
                ErrorAlert.displayCustomAlert("ERROR!","Something Went Wrong\nTry Again!");
//                AlertClass.showAlert("ERROR!!!!!","Something Went Wrong\nTry Again!");
            }
        }

    }
}
