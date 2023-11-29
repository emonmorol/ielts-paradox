package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.StoryInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
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

    public ArrayList<BlogInfo> teacherBlogs(String publisherMail,int limit){
        ArrayList<BlogInfo> blogs = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM blogs WHERE publisherMail = ? LIMIT ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,publisherMail);
                statement.setInt(2,limit);
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

    public int teacherBlogsCount(String publisherMail){
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT COUNT(*) AS row_count FROM blogs WHERE publisherMail = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, publisherMail);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try {
                            int rows = resultSet.getInt("row_count");
                            return rows;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean uploadBlog(BlogInfo si){
        if(si.title == "" || si.title == null || si.content == ""|| si.content== null || si.bandScore == "" || si.bandScore == null) return false;
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();

        String insertQuery = "INSERT INTO blogs (title, date, publisherName, publisherMail, content,thumbnail,bandScore) VALUES (?, ?, ?, ?, ?,?,?)";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, si.title);
                preparedStatement.setString(2, si.date);
                preparedStatement.setString(3, si.publisherName);
                preparedStatement.setString(4, info.email);
                preparedStatement.setString(5, si.content);
                preparedStatement.setString(6, si.thumbnail);
                preparedStatement.setString(7, si.bandScore);

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

    public boolean deleteBlog(String id){
        try {
            Connection connection = new DBConnections().getConnection();
            String query = "DELETE FROM blogs WHERE _id = ?";
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


    public ArrayList<BlogInfo> getMyBlogs(){
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();
        ArrayList<BlogInfo> blogs = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM blogs WHERE publisherMail = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,info.email);
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

}
