package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.Faq;
import com.example.ielts_paradox.utils.DBConnections;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.sql.*;
import java.util.ArrayList;

public class ForCourse {
    public ArrayList<CourseInfo> getAllCourse(int offset,int limit){
        ArrayList<CourseInfo> cf = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM courses LIMIT ? OFFSET ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,limit);
                statement.setInt(2,offset);

                try (ResultSet resultSet = statement.executeQuery()) {
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
        try{
            Connection connection = new DBConnections().getConnection();
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
