package com.example.ielts_paradox.SocketNetworking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OtherController {
    static boolean isRunning = true;
    @FXML
    private Button one;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    void chatH(ActionEvent event) {
        int port = 1235;
        if(!isRunning){
            new MultiThreadedSocketServer().startThreading(port);
            isRunning = true;
        }else{
            new SocketClient().runClient(port);
        }
    }

    @FXML
    void chatH2(ActionEvent event) {
        boolean isRun = true;
        int port = 1236;
        if(!isRun){
            new MultiThreadedSocketServer().startThreading(port);
            isRun = true;
        }else{
            new SocketClient().runClient(port);
        }
    }

    @FXML
    void chatH3(ActionEvent event) {

    }
}
