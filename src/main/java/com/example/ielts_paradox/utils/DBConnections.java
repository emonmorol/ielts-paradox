package com.example.ielts_paradox.utils;

import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.Faq;
import com.example.ielts_paradox.models.UserInfo;

import java.sql.*;
import java.util.ArrayList;

public class DBConnections {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ielts_paradox?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "emon&&MYSQL";


    public boolean validate(String userEmail,String userPass, boolean userType){
        String DB_QUERY = "SELECT * FROM USERS WHERE email = ? AND password = ? AND isTeacher = ?";
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement prpStatement = connection.prepareStatement(DB_QUERY);
            prpStatement.setString(1,userEmail);
            prpStatement.setString(2,userPass);
            prpStatement.setBoolean(3,userType);
            System.out.println(prpStatement);
            ResultSet resultSet = prpStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean isUserExist(String email){
        String DB_QUERY = "SELECT * FROM USERS WHERE email = ?";
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
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
        String DB_QUERY = "INSERT INTO users VALUES (?,?,?,?,?);";
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement prpStatement = connection.prepareStatement(DB_QUERY);
            prpStatement.setString(1,u.email);
            prpStatement.setString(2,u.fullName);
            prpStatement.setString(3,u.contactNumber);
            prpStatement.setString(4,u.password);
            prpStatement.setBoolean(5,u.isTeacher);
            int rowsAffected = prpStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<CourseInfo> getAllCourse(){
        ArrayList<CourseInfo> cf = new ArrayList<>();

        String DB_QUERY = "SELECT * FROM courses";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM courses";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String title = resultSet.getString("title");
                            String thumbnail = resultSet.getString("thumbmail");
                            int price = Integer.parseInt(resultSet.getString("price"));
                            boolean isReleased = Boolean.parseBoolean(resultSet.getString("isReleased"));
                            int discount = Integer.parseInt(resultSet.getString("discount"));;
                            String details = resultSet.getString("details");
                            String instructorName = resultSet.getString("instructorName");
//                        String[] curriculum;
//                        ArrayList<Faq> faqs;
//                        String[] features;
//                        String[] sidebarPoint;
//                        String freateresJSON = resultSet.getString("features");
//                        String curriculumJSON = resultSet.getString("curriculum");
//                        String faqsJSON = resultSet.getString("faq");
//                        String sidebarPointJSON = resultSet.getString("sidebarPoint");
                            CourseInfo ci = new CourseInfo(_id,title,thumbnail,price,isReleased,discount,details,instructorName);
                            cf.add(ci);
                        }catch (SQLException e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cf;
    }

}
