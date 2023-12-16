package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.*;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ForChat {
    public boolean isRunning(int portName){
        String DB_QUERY = "SELECT * FROM port_history WHERE port_name = ?";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DB_QUERY)) {
                preparedStatement.setInt(1, portName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try{
                            boolean is = resultSet.getBoolean("isRunning");
                            return is;
                        }catch (SQLException e){
                            e.printStackTrace();
                        }

                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void updatePort(int portName,boolean value){
        String updateSql = "UPDATE port_history SET isRunning = ? WHERE port_name = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setBoolean(1, value);
            preparedStatement.setInt(2, portName);
            int rowsAffected = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<MessageInfo> getConversation(String id,boolean isCourse){
        ArrayList<MessageInfo> mis = new ArrayList<>();

        String DB_QUERY;
        if(isCourse){
            DB_QUERY = "SELECT * FROM messages_history WHERE courseId = ? AND isCourse = ?";
        }else{
            DB_QUERY = "SELECT * FROM messages_history WHERE examId = ? AND isCourse = ?";
        }
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DB_QUERY)) {
                preparedStatement.setInt(1, Integer.parseInt(id));
                preparedStatement.setBoolean(2, isCourse);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            String senderName = resultSet.getString("senderName");
                            String sendedEmail = resultSet.getString("senderEmail");
                            String message = resultSet.getString("message");
                            MessageInfo b = new MessageInfo(message,senderName,sendedEmail);

                            mis.add(b);
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mis;
    }

    public void updateConversation(String id,boolean isCourse,String message,String name,String email){
        String insertQuery = "INSERT INTO messages_history (courseId, examId, isCourse, senderName, senderEmail,message) VALUES (?, ?, ?, ?, ?, ?)";

        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                if(isCourse){
                    preparedStatement.setInt(1, Integer.parseInt(id));
                    preparedStatement.setInt(2, -1);
                }else {
                    preparedStatement.setInt(1, -1);
                    preparedStatement.setInt(2, Integer.parseInt(id));
                }

                preparedStatement.setBoolean(3, isCourse);
                preparedStatement.setString(4, name);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, message);
                int rowsAffected = preparedStatement.executeUpdate();
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
