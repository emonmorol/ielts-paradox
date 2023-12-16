package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.PaidStudentInfo;
import com.example.ielts_paradox.models.StoryInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;

import java.sql.*;
import java.util.ArrayList;

public class ForStories {
    public ArrayList<StoryInfo> getAllStories(int offset,int limit){
        ArrayList<StoryInfo> blogs = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM success_stories LIMIT ? OFFSET ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,limit);
                statement.setInt(2,offset);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String mainStory = resultSet.getString("mainStory");
                            String studentImage = resultSet.getString("studentImage");
                            String studentName = resultSet.getString("studentName");
                            String bandScore = resultSet.getString("bandScore");
                            StoryInfo b = new StoryInfo(_id,mainStory,studentImage,studentName,bandScore);
                            System.out.println();
                            blogs.add(b);
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
        return blogs;
    }

    public StoryInfo getStoryById(String id){
        StoryInfo ci;

        String DB_QUERY = "SELECT * FROM success_stories WHERE _id = ?";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DB_QUERY)) {
                preparedStatement.setInt(1, Integer.parseInt(id));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String mainStory = resultSet.getString("mainStory");
                            String studentImage = resultSet.getString("studentImage");
                            String studentName = resultSet.getString("studentName");
                            String bandScore = resultSet.getString("bandScore");
                            StoryInfo b = new StoryInfo(_id,mainStory,studentImage,studentName,bandScore);
                            return b;
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
        return null;
    }

    public boolean uploadStory(StoryInfo si){
        if(si.bandScore == "" || si.bandScore == null || si.studentName == " " || si.studentName == null || si.mainStory == ""|| si.mainStory== null) return false;
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();

        String insertQuery = "INSERT INTO success_stories (mainStory, studentImage, studentName, bandScore, teacherMail) VALUES (?, ?, ?, ?, ?)";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, si.mainStory);
                preparedStatement.setString(2, si.studentImage);
                preparedStatement.setString(3, si.studentName);
                preparedStatement.setString(4, si.bandScore);
                preparedStatement.setString(5, info.email);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<StoryInfo> getMyStories(){
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();
        ArrayList<StoryInfo> blogs = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM success_stories WHERE teacherMail = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,info.email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String mainStory = resultSet.getString("mainStory");
                            String studentImage = resultSet.getString("studentImage");
                            String studentName = resultSet.getString("studentName");
                            String bandScore = resultSet.getString("bandScore");
                            StoryInfo b = new StoryInfo(_id,mainStory,studentImage,studentName,bandScore);
                            System.out.println();
                            blogs.add(b);
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
        return blogs;
    }

    public boolean deleteStory(String id){
        try {
            Connection connection = new DBConnections().getConnection();
            String query = "DELETE FROM success_stories WHERE _id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(id));

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
