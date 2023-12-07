package com.example.ielts_paradox.models;

public class MessageInfo {

    public String senderName;
    public String senderEmail;
    public String message;
    public MessageInfo(String message, String senderName, String senderEmail) {

        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.message = message;
    }
}
