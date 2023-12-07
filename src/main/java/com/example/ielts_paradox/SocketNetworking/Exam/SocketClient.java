package com.example.ielts_paradox.SocketNetworking.Exam;

import com.example.ielts_paradox.controllers.MessagesController.CourseChatController;
import com.example.ielts_paradox.controllers.MessagesController.ExamChatController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    private static PrintWriter out;
    private ExamChatController controller;
    private Scanner in;

    public void runClient(int port,String mail,String id) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/messages/mock_chat.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.setData(mail,id);
            Image logo = new Image(getClass().getResource("/images/logo.png").toExternalForm());
            primaryStage.setTitle("IELTS ParadOx Messenger");
            primaryStage.getIcons().add(logo);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            Socket socket = new Socket("localhost", port);

            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            Runnable serverRunnable = () -> connectToServer(port);
            new Thread(serverRunnable).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer(int port) {
        try {
            while (in.hasNext()) {
                String message = in.nextLine();
                controller.appendToLog(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToServer(String message) {
        out.println(message);
    }
}