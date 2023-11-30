package com.example.ielts_paradox.controllers.alertController;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForProfile;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.SceneChanger;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FormAlertController {
    @FXML
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private VBox customAlert;
    @FXML
    private Label title;
    private Stage dialogStage;
    static String id_;

    @FXML
    private MFXTextField data;
    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    public void setTitle(String m) {
        title.setText(m);
    }

    public void closeAlert() {
        dialogStage.close();
    }

    public static void setId_(String id){
        id_ = id;
    }

    public void saveData(ActionEvent e) throws IOException {
        
        String v = data.getText();

        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();

        String type = title.getText();
        if(type == null || type == ""){
            ErrorAlert.displayCustomAlert("Error!","Fill up the text field\nTry Again.");
        }
        else if(type == "Email"){
            boolean isUpdated = new ForProfile().updateEmail(info.email,v);
            System.out.println(isUpdated);
            if(isUpdated){
                user.setUser(new UserInfo(info.fullName,v,info.contactNumber,info.isTeacher,info.bio));
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed","Can not Update\nTry Again!");
            }
        } else if (type == "Full Name") {
            boolean isUpdated = new ForProfile().updateName(info.email,v);
            if(isUpdated){
                user.setUser(new UserInfo(v,info.email,info.contactNumber,info.isTeacher,info.bio));
//                new SceneChanger().switchScene(e,"/fxmls/students/studentDashboard.fxml");
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed","Can not Update\nTry Again!");
            }
        }else if (type == "Contact") {
            boolean isUpdated = new ForProfile().updateContact(info.email,v);
            if(isUpdated){
                user.setUser(new UserInfo(info.fullName,info.email,v,info.isTeacher,info.bio));
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed","Can not Update\nTry Again!");
            }
        }else if (type == "Password") {
            boolean isUpdated = new ForProfile().updatePassword(info.email,v);
            if(isUpdated){
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed","Can not Update\nTry Again!");
            }

        }
        else if (type == "Bio") {
            boolean isUpdated = new ForProfile().updateBio(info.email,v);
            if(isUpdated){
                user.setUser(new UserInfo(info.fullName,info.email,info.contactNumber,info.isTeacher,v));
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed","Can not Update\nTry Again!");
            }
        } else if (type == "Enter Question Link Here!") {
            boolean isUpdated = new ForTest().updateQuestionLink(id_,v);
            if(isUpdated){
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed","Can not Update\nTry Again!");
            }
        }
        else if (type == "Enter Practice Question Link Here!") {
            boolean isUpdated = new ForTest().updatePracticeQuestionLink(id_,v);
            if(isUpdated){
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed","Can not Update\nTry Again!");
            }
        }
        else if (type == "Enter Updated Meet Link Here!") {
            boolean isUpdated = new ForTest().updateMeetLink(id_,v);
            if(isUpdated){
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed","Can not Update\nTry Again!");
            }
        }

        dialogStage.close();
    }
}
