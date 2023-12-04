package com.example.ielts_paradox.SocketNetworking;

import com.example.ielts_paradox.controllers.MessagesController.ChatController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    private static PrintWriter out;
    private ChatController controller;

    public void runClient(int port) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/messages/chat.fxml"));
            Parent root = loader.load();

            controller = loader.getController();

            primaryStage.setTitle("Socket Client");
            primaryStage.setScene(new Scene(root));
//            primaryStage.setOnCloseRequest(e -> System.exit(0));
            primaryStage.show();

            Runnable serverRunnable = () -> connectToServer(port);
            new Thread(serverRunnable).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer(int port) {
        try {
            Socket socket = new Socket("localhost", port);
            Scanner in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            while (in.hasNext()) {
                String message = in.nextLine();
                String[] sm = message.split("\\$");
                controller.appendToLog(sm[0],sm[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToServer(String message) {
        out.println(message);
    }
}
