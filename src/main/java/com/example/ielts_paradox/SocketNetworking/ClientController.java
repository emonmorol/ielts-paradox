package com.example.ielts_paradox.SocketNetworking;// ClientController.java
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {

    @FXML
    private TextArea logArea;

    @FXML
    private TextField inputField;

    @FXML
    private Button sendButton;


    @FXML
    private void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            // Handle send button action
            appendToLog("You: " + message);
            inputField.clear();

            // Send the message to the server
            SocketClient.sendMessageToServer(message);
        }
    }

    public void appendToLog(String message) {
        logArea.appendText(message + "\n");
    }

    public TextField getInputField() {
        return inputField;
    }
}
