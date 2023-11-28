package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;

import java.sql.*;

public class ForUser {
    public boolean validate(String userEmail,String userPass, boolean userType){
        String DB_QUERY = "SELECT * FROM USERS WHERE email = ? AND password = ? AND isTeacher = ?";

        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement prpStatement = connection.prepareStatement(DB_QUERY)) {
                prpStatement.setString(1,userEmail);
                prpStatement.setString(2,userPass);
                prpStatement.setBoolean(3,userType);
                try (ResultSet resultSet = prpStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try{
                            String email = resultSet.getString("email");
                            String fullname = resultSet.getString("fullname");
                            String contact_number = resultSet.getString("contact_number");
                            Boolean isTeacher = resultSet.getBoolean("isTeacher");
                            String bio = resultSet.getString("bio");
                            UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
                            user.setUser(new UserInfo(fullname,email,contact_number,isTeacher,bio));
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                        return true;

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isUserExist(String email){
        String DB_QUERY = "SELECT * FROM USERS WHERE email = ?";
        try{
            Connection connection = new DBConnections().getConnection();
            PreparedStatement prpStatement = connection.prepareStatement(DB_QUERY);
            prpStatement.setString(1,email);

            ResultSet resultSet = prpStatement.executeQuery();
            if(resultSet.next()){

                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean createUser(UserInfo u){
        String DB_QUERY = "INSERT INTO users VALUES (?,?,?,?,?,?);";
        try{
            Connection connection = new DBConnections().getConnection();
            PreparedStatement prpStatement = connection.prepareStatement(DB_QUERY);
            prpStatement.setString(1,u.email);
            prpStatement.setString(2,u.fullName);
            prpStatement.setString(3,u.contactNumber);
            prpStatement.setString(4,u.password);
            prpStatement.setBoolean(5,u.isTeacher);
            prpStatement.setString(6,u.bio);
            int rowsAffected = prpStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
