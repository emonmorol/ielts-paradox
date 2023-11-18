package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.utils.DBConnections;

import java.sql.*;
import java.util.ArrayList;

public class ForBlogs {
    public ArrayList<BlogInfo> getAllBlog(int offset,int limit){
        ArrayList<BlogInfo> blogs = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM blogs LIMIT ? OFFSET ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,limit);
                statement.setInt(2,offset);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String title = resultSet.getString("title");
                            String thumbnail = resultSet.getString("thumbnail");
                            String publisherName = resultSet.getString("publisherName");
                            String content = resultSet.getString("content");
                            String date = resultSet.getString("date");
                            String bandScore = resultSet.getString("bandScore");
                            BlogInfo b = new BlogInfo(title,_id,date,publisherName,content,thumbnail,bandScore);
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

    public BlogInfo getBlogById(String id){
        BlogInfo ci;

        String DB_QUERY = "SELECT * FROM blogs WHERE _id = ?";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DB_QUERY)) {
                preparedStatement.setInt(1, Integer.parseInt(id));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String title = resultSet.getString("title");
                            String thumbnail = resultSet.getString("thumbnail");
                            String publisherName = resultSet.getString("publisherName");
                            String content = resultSet.getString("content");
                            String date = resultSet.getString("date");
                            String bandScore = resultSet.getString("bandScore");
                            BlogInfo b = new BlogInfo(title,_id,date,publisherName,content,thumbnail,bandScore);
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
