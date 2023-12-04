package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.SocketNetworking.*;
import com.example.ielts_paradox.controllers.CourseContentController;
import com.example.ielts_paradox.controllers.MessagesController.ChatController;
import com.example.ielts_paradox.database.ForChat;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CourseCardHandler {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label id;

    @FXML
    private Label instructor;

    @FXML
    private Label title;

    @FXML
    private Label msgPort;

    public void continueActionHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/courseContent.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        CourseContentController ccc = fxmlLoader.getController();
        CourseInfo ci = new ForCourse().getCourseById(id.getText());
        ccc.setData(ci,"0");
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void chatHandler(ActionEvent event) throws IOException {
        if(!new ForChat().isRunning(Integer.parseInt(msgPort.getText()))){
            new MultiThreadedSocketServer().startThreading(Integer.parseInt(msgPort.getText()));
            new ForChat().updatePort(Integer.parseInt(msgPort.getText()),true);
        }


        new SocketClient().runClient(Integer.parseInt(msgPort.getText()));

    }

    public void setData(CourseInfo ci){
        id.setText(ci._id);
        System.out.println(ci._id);
        instructor.setText(ci.instructorName);
        title.setText(ci.title);
        msgPort.setText(ci.messagePort+"");
    }
}
