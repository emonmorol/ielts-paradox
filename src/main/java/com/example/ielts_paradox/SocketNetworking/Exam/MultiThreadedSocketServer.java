package com.example.ielts_paradox.SocketNetworking.Exam;
import com.example.ielts_paradox.database.ForChat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadedSocketServer{

    private List<ClientHandler> clients = new ArrayList<>();
    private AtomicInteger clientCounter = new AtomicInteger(1);
    private int runningPort;


    public void startThreading(int port) {
        runningPort = port;
        Runnable serverRunnable = () -> startServer(port);
        new Thread(serverRunnable).start();
    }

    private void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Listening on port "+port+"...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private Scanner in;
        private PrintWriter out;
        private String clientName;
        private String email;
        private String test_id;

        public ClientHandler(Socket socket) {
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
            try {
                String s = in.nextLine();
                if(s!=null){
                    String[] ss = s.split("\\$");
                    this.clientName = ss[0];
                    this.email = ss[1];
                    this.test_id = ss[2];


                    broadcastMessage(clientName + " has joined the chat!",this);
                    System.out.println("Client " + clientName + " connected: " + clientSocket);
                    while (true) {
                        if (in.hasNext()) {

                            String message = in.nextLine();
                            System.out.println(message);
                            if(message.equals("/disconnect")){
                                broadcastMessage(clientName+" has left the chat",this);
                                break;
                            }
                            broadcastMessage(clientName + "$" + message, this);
                        }
                    }
                }

            }
            catch (Exception e){
                broadcastMessage(clientName+" has left the chat!",this);
                e.printStackTrace();
            }
            finally {
                in.close();
                out.close();
                if(clients.size()==1){
                    new ForChat().updatePort(runningPort,false);
                }
                clients.remove(this);
                closeConnection();

                System.out.println("Client " + clientName + " disconnected: " + clientSocket);
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        private void closeConnection(){
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void broadcastMessage(String message, ClientHandler sender) {
        System.out.println("Broadcasting: " + message);
        String[] ss = message.split("\\$");
        if(ss.length<4){
            return;
        }
        for (ClientHandler client : clients) {
            System.out.println(client.clientName + "    "+client.email + "    "+client.test_id + "    "+client.clientSocket);
            System.out.println(ss[0] + "    "+ss[1] + "    "+ss[2] + "    "+ss[3]);
            System.out.println();
            if(client.clientName == null) continue;
            if(client != sender && client.test_id.equals(ss[1])){
                client.sendMessage(ss[0]+"$"+ss[3]);
                break;
            }
        }
    }

}