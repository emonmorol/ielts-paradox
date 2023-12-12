package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.MessageInfo;
import com.example.ielts_paradox.models.NoticeInfo;
import com.example.ielts_paradox.models.UserInfo;
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

public class ForNotices {
    public ArrayList<NoticeInfo> getNotices(){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        ArrayList<NoticeInfo> nis = new ArrayList<>();
        System.out.println(info.email);

        String DB_QUERY = "SELECT * FROM notices WHERE recieverMail = ?";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DB_QUERY)) {
                preparedStatement.setString(1, info.email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            int _id = resultSet.getInt("_id");
                            String text = resultSet.getString("text");
                            String title = resultSet.getString("title");
                            String recieverMail = resultSet.getString("recieverMail");
                            String senderMail = resultSet.getString("senderMail");
                            String senderName = resultSet.getString("senderName");
                            String module = resultSet.getString("module");
                            NoticeInfo ni = new NoticeInfo(_id,text,title,recieverMail,senderMail,senderName,module);
                            System.out.println(ni.toString());
                            nis.add(ni);
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nis;
    }

    public NoticeInfo getNoticeById(String id){


        String DB_QUERY = "SELECT * FROM notices WHERE _id = ?";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DB_QUERY)) {
                preparedStatement.setString(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try{
                            int _id = resultSet.getInt("_id");
                            String text = resultSet.getString("text");
                            String title = resultSet.getString("title");
                            String recieverMail = resultSet.getString("recieverMail");
                            String senderMail = resultSet.getString("senderMail");
                            String senderName = resultSet.getString("senderName");
                            String module = resultSet.getString("module");

                           return new NoticeInfo(_id,text,title,recieverMail,senderMail,senderName,module);
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean uploadNotices(NoticeInfo ni){

        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();

        String insertQuery = "INSERT INTO notices (text, title, recieverMail, senderMail, senderName,module) VALUES (?, ?, ?, ?, ?,?)";

        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, ni.text);
                preparedStatement.setString(2, ni.title);
                preparedStatement.setString(3, ni.recieverMail);
                preparedStatement.setString(4, ni.senderMail);
                preparedStatement.setString(5, ni.senderName);
                preparedStatement.setString(6, ni.module);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
