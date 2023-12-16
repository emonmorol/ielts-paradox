package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.*;
import com.example.ielts_paradox.singletons.UserSingleTon;
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
                            int messagePort = resultSet.getInt("messagePort");
                            CourseInfo ci = new CourseInfo(_id,title,thumbnail,price,isReleased,discount,details,instructorName,messagePort);
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
                            String routine = resultSet.getString("routine");
                            int messagePort = resultSet.getInt("messagePort");
                            String videoJSON = resultSet.getString("content");

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

                            ArrayList<CourseVideo> cvs = new ArrayList<>();

                            JsonArray jsonArray2 = gson.fromJson(videoJSON, JsonArray.class);
                            for (JsonElement element : jsonArray2) {
                                CourseVideo ob = gson.fromJson(element, CourseVideo.class);
                                cvs.add(ob);
                            }

                            ci = new CourseInfo(_id,title,features,thumbnail,price,isReleased,discount,curriculum,faqs,details,sidebarPoint,instructorName,routine,messagePort,cvs);
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

    public ArrayList<CourseInfo> teacherCourses(String instructorMail,int limit){
        ArrayList<CourseInfo> cf = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM courses WHERE instructorMail = ? LIMIT ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,instructorMail);
                statement.setInt(2,limit);

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
                            int messagePort = resultSet.getInt("messagePort");
                            CourseInfo ci = new CourseInfo(_id,title,thumbnail,price,isReleased,discount,details,instructorName,messagePort);
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

    public int teacherCourseCount(String instructorMail){
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT COUNT(*) AS row_count FROM courses WHERE instructorMail = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, instructorMail);
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

    public boolean deleteCourse(String id){
        try {
            Connection connection = new DBConnections().getConnection();
            String query = "DELETE FROM courses WHERE _id = ?";
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

    public boolean uploadCourse(String title, int price,int discount,String details,String features,String curriculum,String faq,String sidebarPoint,String thumbnail,String routine,int messagePort,String content){
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();

        String insertQuery = "INSERT INTO courses (title, price, isReleased, discount, details,features,curriculum,faq,sidebarPoint,thumbmail,instructorName,instructorMail,routine,messagePort,content) VALUES (?, ?, ?, ?, ?,?,?, ?, ?, ?, ?,?,?,?,?)";
        String insertQuery2 = "INSERT INTO port_history (messagePort,isRunning) VALUES (?, ?)";

        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement2 = connection.prepareStatement(insertQuery)) {
                preparedStatement2.setInt(1, messagePort);
                preparedStatement2.setBoolean(2, false);
                int rowsAffected = preparedStatement2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement2 = connection.prepareStatement(insertQuery)) {
                preparedStatement2.setInt(1, messagePort);
                preparedStatement2.setBoolean(2, false);
                int rowsAffected = preparedStatement2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, title);
                preparedStatement.setInt(2, price);
                preparedStatement.setBoolean(3, true);
                preparedStatement.setInt(4, discount);
                preparedStatement.setString(5, details);
                preparedStatement.setString(6, features);
                preparedStatement.setString(7, curriculum);
                preparedStatement.setString(8, faq);
                preparedStatement.setString(9, sidebarPoint);
                preparedStatement.setString(10, thumbnail);
                preparedStatement.setString(11, info.fullName);
                preparedStatement.setString(12, info.email);
                preparedStatement.setString(13, routine);
                preparedStatement.setInt(14, messagePort);
                preparedStatement.setString(15, content);

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

    public int getStudentCount(int courseId,boolean isAccepted){
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT COUNT(*) AS row_count FROM paid_student WHERE courseId = ? AND courseApproval = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, courseId);
                preparedStatement.setBoolean(2, isAccepted);

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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
