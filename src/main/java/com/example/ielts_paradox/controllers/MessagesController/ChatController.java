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

public class ChatController {


    @FXML
    public Label courseName;

    @FXML
    public VBox messageVBox;
    @FXML
    private TextField inputField;

//    @FXML
//    private TextArea logArea;

//    @FXML
//    public TextField messageWriter;
    @FXML
    public Label msgPort;

    @FXML
    public ScrollPane sPane;
    public MFXButton sendButton;

    public void setData(CourseInfo c){
        courseName.setText(c.title);
        msgPort.setText(c.messagePort+"");
    }

    @FXML
    private void sendMessage() throws IOException {
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        String message = inputField.getText();
        if (!message.isEmpty()) {
            // Handle send button action
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/messages/outgoingCard.fxml"));
            AnchorPane outg = fxmlLoader.load();
            OutgoingCard oc = fxmlLoader.getController();
            oc.setData(message);
//            messageVBox.setVgrow(outg, Priority.ALWAYS);
            sPane.setVvalue(1.0);
            messageVBox.getChildren().add(outg);


//            appendToLog("You: " + message);
            inputField.clear();

            // Send the message to the server
            SocketClient.sendMessageToServer(message);
        }
    }

    public void appendToLog(String message) throws IOException {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/messages/incomingCard.fxml"));
            AnchorPane outg = null;
            try {
                outg = fxmlLoader.load();
                IncomingCard oc = fxmlLoader.getController();
                oc.setData(message);
//                messageVBox.setVgrow(outg, Priority.ALWAYS);
                sPane.setVvalue(1.0);
                messageVBox.getChildren().add(outg);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }

    public TextField getInputField() {
        return inputField;
    }

}
