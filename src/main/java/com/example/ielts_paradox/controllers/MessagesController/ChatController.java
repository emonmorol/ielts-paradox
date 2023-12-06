package com.example.ielts_paradox.controllers.MessagesController;
import com.example.ielts_paradox.SocketNetworking.SocketClient;
import com.example.ielts_paradox.controllers.cardControllers.CourseCardHandler;
import com.example.ielts_paradox.controllers.cardControllers.TeacherMyCourseCardController;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatController implements Initializable{


    @FXML
    public Label courseName;

    @FXML
    public VBox messageVBox;
    @FXML
    private TextField inputField;
    @FXML
    public Label msgPort;

    @FXML
    public MFXScrollPane sPane;
    public MFXButton sendButton;

    boolean isJoined = false;
    boolean isTeacher;

    String name;

    public void setData(CourseInfo c){
        courseName.setText(c.title);
        msgPort.setText(c.messagePort+"");
    }

    @FXML
    private void sendMessage() throws IOException {
        UserInfo user = UserSingleTon.getInstance(new UserInfo()).getUser();
//        Platform.runLater(() -> {
//            if(!isNameSpecified){
//                if(user.isTeacher){
//                    SocketClient.sendMessageToServer(user.fullName+" (Instructor)");
//                }else{
//                    SocketClient.sendMessageToServer(user.fullName);
//                }
//                isNameSpecified = true;
//            }
//        });
//
//        if(!isJoined){
//            if(isTeacher){
//                SocketClient.sendMessageToServer(name+" (Instructor)");
//            }else{
//                SocketClient.sendMessageToServer(name);
//            }
//            isJoined = true;
//        }

        String message = inputField.getText();
        if (!message.isEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/messages/outgoingCard.fxml"));
            AnchorPane outg = fxmlLoader.load();
            OutgoingCard oc = fxmlLoader.getController();
            oc.setData(user.fullName,message);

            sPane.setVvalue(1.0);
            messageVBox.getChildren().add(outg);
            inputField.clear();
            if(user.isTeacher)
                SocketClient.sendMessageToServer(user.fullName+" (Instructor)$"+message);
            else
                SocketClient.sendMessageToServer(user.fullName+"$"+message);
        }
    }

    public void appendToLog(String message) throws IOException {
        Platform.runLater(() -> {
            String[] s = message.split("\\$");
            if(s.length <2){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/messages/messageLabelCard.fxml"));
                AnchorPane outg = null;
                try {
                    outg = fxmlLoader.load();
                    LabelController lc = fxmlLoader.getController();
                    lc.setMsgLabel(message);
                    sPane.setVvalue(1.0);
                    messageVBox.getChildren().add(outg);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/messages/incomingCard.fxml"));
                AnchorPane outg = null;
                try {
                    outg = fxmlLoader.load();
                    IncomingCard oc = fxmlLoader.getController();
                    oc.setData(s[0],s[1]);
                    sPane.setVvalue(1.0);
                    messageVBox.getChildren().add(outg);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        });

    }

    public TextField getInputField() {
        return inputField;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sPane.setVvalue(1.0);
        try{
            UserInfo user = UserSingleTon.getInstance(new UserInfo()).getUser();
            Platform.runLater(() -> {
                name = user.fullName;
                isTeacher = user.isTeacher;
                Stage stage = (Stage) messageVBox.getScene().getWindow();
                stage.setOnCloseRequest(event -> {
                    SocketClient.sendMessageToServer("/disconnect");
                });
            });
        }catch (Exception e){
            Stage stage = (Stage) messageVBox.getScene().getWindow();
            stage.close();
            e.printStackTrace();
        }
    }
}