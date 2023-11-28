package com.example.ielts_paradox.controllers.alertController;

import com.example.ielts_paradox.database.ForProfile;
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

public class Alert2Controller {
    @FXML
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private VBox customAlert;
    @FXML
    private Label title;

    private Stage dialogStage;
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
    public void saveData(ActionEvent e) throws IOException {
        String v = data.getText();

        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();

        String type = title.getText();

        if(type == "Email"){
            boolean isUpdated = new ForProfile().updateEmail(info.email,v);
            System.out.println(isUpdated);
            if(isUpdated){
                user.setUser(new UserInfo(info.fullName,v,info.contactNumber,info.isTeacher,info.bio));
            }
        } else if (type == "Full Name") {
            boolean isUpdated = new ForProfile().updateName(info.email,v);
            if(isUpdated){
                user.setUser(new UserInfo(v,info.email,info.contactNumber,info.isTeacher,info.bio));
                new SceneChanger().switchScene(e,"/fxmls/students/studentDashboard.fxml");
            }
        }else if (type == "Contact") {
            boolean isUpdated = new ForProfile().updateContact(info.email,v);
            if(isUpdated){
                user.setUser(new UserInfo(info.fullName,info.email,v,info.isTeacher,info.bio));
            }
        }else if (type == "Password") {
            boolean isUpdated = new ForProfile().updatePassword(info.email,v);

        }
        else if (type == "Bio") {
            boolean isUpdated = new ForProfile().updateBio(info.email,v);
            if(isUpdated){
                user.setUser(new UserInfo(info.fullName,info.email,info.contactNumber,info.isTeacher,v));
            }
        }

        dialogStage.close();
    }
}
