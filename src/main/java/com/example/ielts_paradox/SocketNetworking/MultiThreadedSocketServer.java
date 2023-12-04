package com.example.ielts_paradox.SocketNetworking;

import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadedSocketServer{

    private TextArea logArea = new TextArea();
    private List<ClientHandler> clients = new ArrayList<>();
    private AtomicInteger clientCounter = new AtomicInteger(1);


    public void startThreading(int port) {
        Stage stage = new Stage();
        logArea.setEditable(false);
        stage.setTitle("MultiThreaded Socket Server");
        stage.setScene(new Scene(logArea, 400, 300));
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();

        Runnable serverRunnable = () -> startServer(port);
        new Thread(serverRunnable).start();
    }

    private void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            appendToLog("Server started. Listening on port 5555...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                appendToLog("Client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendToLog(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private Scanner in;
        private PrintWriter out;
        private String clientName;

        public ClientHandler(Socket socket) {
            UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
            System.out.println(info.fullName);
            this.clientName = info.fullName;
            this.clientSocket = socket;
            try {
                this.in = new Scanner(socket.getInputStream());
                this.out = new PrintWriter(socket.getOutputStream(), true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            UserInfo userInfo = UserSingleTon.getInstance(new UserInfo()).getUser();
            appendToLog("Client " + userInfo.fullName + " connected: " + clientSocket);
            try {
                while (true) {
                    if (in.hasNext()) {
                        String message = in.nextLine();
                        broadcastMessage(userInfo.fullName + "$" + message, this);
                    }
                }
            } finally {
                in.close();
                out.close();
                clients.remove(this);
                appendToLog("Client " + userInfo.fullName + " disconnected: " + clientSocket);
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }


    private void broadcastMessage(String message, ClientHandler sender) {
        appendToLog("Broadcasting: " + message);
        for (ClientHandler client : clients) {
            // Send the message to all clients except the sender
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
}
