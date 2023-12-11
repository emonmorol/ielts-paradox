package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.CourseVideo;
import com.example.ielts_paradox.models.Faq;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class ForCourseContent {
    public ArrayList<CourseVideo> getCourseContent(int Id){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        ArrayList<CourseVideo> courses = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT content FROM paid_student WHERE courseId = ? AND email = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, Id);
                preparedStatement.setString(2, info.email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        try {
                            Gson gson = new Gson();
                            String videoJSON = resultSet.getString("content");
                            JsonArray jsonArray = gson.fromJson(videoJSON, JsonArray.class);
                            for (JsonElement element : jsonArray) {
                                CourseVideo ob = gson.fromJson(element, CourseVideo.class);
                                courses.add(ob);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    public boolean updateWatch(String json,int courseID){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();

        String updateSql = "UPDATE paid_student SET content = ? WHERE courseId = ? AND email = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, json);
            preparedStatement.setInt(2, courseID);
            preparedStatement.setString(3, info.email);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected >0);
        } catch (SQLException e) {
            return false;
        }
    }
}
