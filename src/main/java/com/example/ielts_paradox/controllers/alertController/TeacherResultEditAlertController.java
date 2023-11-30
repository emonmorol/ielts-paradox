package com.example.ielts_paradox.controllers.alertController;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeacherResultEditAlertController {
    String id_;
    @FXML
    private Stage stage;
    private Parent root;
    private Scene scene;
    private Stage dialogStage;

    @FXML
    private TextField bandScore;

    @FXML
    private TextField resultLink;
    public void setId_(String m){id_= m;};
    @FXML
    void upload(ActionEvent event) {
        String score = bandScore.getText();
        String link = resultLink.getText();
        if(score != null && link!=null && score != "" && link!=""){
            boolean isUpaded = new ForTest().updateResult(id_,score,link);
            if(isUpaded){
                SuccessAlert.displayCustomAlert();
            }else{
                ErrorAlert.displayCustomAlert("Failed!","Can't Update Score\nTry Again!");
            }
        }else{
            ErrorAlert.displayCustomAlert("Error!","Fill up the text field\n Try Again.");
        }
    }
    @FXML
    public void closeAlert() {
        dialogStage.close();
    }

    @FXML
    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

}
