package com.example.ielts_paradox.controllers.MessagesController;

import com.example.ielts_paradox.database.ForChat;
import com.example.ielts_paradox.models.CourseInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseChatCardController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label courseId;

    @FXML
    private Label instructor;

    @FXML
    private Label title;

    @FXML
    private Label msgPort;

    @FXML
    void getToChat(ActionEvent event) throws IOException {
        int prt = Integer.parseInt(msgPort.getText());
        System.out.println("Port = "+prt);
        boolean isRun = new ForChat().isRunning(prt);
        if(!isRun){
//            new ForChat().updatePort(prt,true);
//            ServerMain sm = new ServerMain(Integer.parseInt(msgPort.getText()));
//            Thread t = new Thread(sm);
//            t.start();


            stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/msg.fxml"));
            root = loader.load();
            scene = new Scene(root);

            stage.setTitle("Messages");
            stage.setScene(scene);
            stage.show();

        }else{
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/msg.fxml"));
            root = loader.load();
            scene = new Scene(root);

            stage.setTitle("Messages");
            stage.setScene(scene);
            stage.show();

        }

    }

    public void setData(CourseInfo c){
        courseId.setText(c._id);
        instructor.setText(c.instructorName);
        title.setText(c.title);
        msgPort.setText(c.messagePort+"");
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
