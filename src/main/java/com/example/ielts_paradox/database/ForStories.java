package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.StoryInfo;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
