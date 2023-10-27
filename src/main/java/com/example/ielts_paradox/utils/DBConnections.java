package com.example.ielts_paradox.utils;

import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.Faq;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.sql.*;
import java.util.ArrayList;

public class DBConnections {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ielts_paradox?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "emon&&MYSQL";


    public boolean validate(String userEmail,String userPass, boolean userType){
        String DB_QUERY = "SELECT * FROM USERS WHERE email = ? AND password = ? AND isTeacher = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
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
                            UserSingleTon user = UserSingleTon.getInstance(new UserInfo(fullname,email,contact_number,isTeacher));
                            System.out.println(email);
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
    public CourseInfo getCourseById(String id){
        CourseInfo ci;

        String DB_QUERY = "SELECT * FROM courses WHERE _id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DB_QUERY)) {
                preparedStatement.setInt(1, Integer.parseInt(id));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String title = resultSet.getString("title");
                            String thumbnail = resultSet.getString("thumbmail");
                            int price = Integer.parseInt(resultSet.getString("price"));
                            boolean isReleased = Boolean.parseBoolean(resultSet.getString("isReleased"));
                            int discount = Integer.parseInt(resultSet.getString("discount"));;
                            String details = resultSet.getString("details");
                            String instructorName = resultSet.getString("instructorName");



                            String freateresJSON = resultSet.getString("features");
                            String curriculumJSON = resultSet.getString("curriculum");
                            String faqsJSON = resultSet.getString("faq");
                            String sidebarPointJSON = resultSet.getString("sidebarPoint");

                            Gson gson = new Gson();
                            String[] features = gson.fromJson(freateresJSON, String[].class);
                            String[] curriculum = gson.fromJson(curriculumJSON, String[].class);
                            String[] sidebarPoint = gson.fromJson(sidebarPointJSON, String[].class);

                            JsonArray jsonArray = gson.fromJson(faqsJSON, JsonArray.class);

                            ArrayList<Faq> faqs = new ArrayList<>();

                            for (JsonElement element : jsonArray) {
                                Faq ob = gson.fromJson(element, Faq.class);
                                faqs.add(ob);
                            }

                            ci = new CourseInfo(_id,title,features,thumbnail,price,isReleased,discount,curriculum,faqs,details,sidebarPoint,instructorName);
                            return ci;
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

}
