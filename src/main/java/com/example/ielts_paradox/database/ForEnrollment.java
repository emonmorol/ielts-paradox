package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.*;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.sql.*;
import java.util.ArrayList;

public class ForEnrollment {
    public boolean validate(String userEmail,String id){
        String DB_QUERY = "SELECT * FROM paid_student WHERE email = ? AND courseId = ?";

        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement prpStatement = connection.prepareStatement(DB_QUERY)) {
                prpStatement.setString(1,userEmail);
                prpStatement.setString(2,id);
                try (ResultSet resultSet = prpStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean courseEnrollment(PaidStudentInfo psi){
        String insertQuery = "INSERT INTO paid_student (bkashNumber, transectionId, email, courseId, enrollementDate, courseApproval, isExpired,content) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                Gson gson = new Gson();
                JsonArray jsonArray = new JsonArray();
                for (CourseVideo course : psi.content) {
                    JsonElement jsonElement = gson.toJsonTree(course);
                    jsonArray.add(jsonElement);
                }
                String jsonString = gson.toJson(jsonArray);

                preparedStatement.setString(1, psi.bkashNumber);
                preparedStatement.setString(2, psi.transectionId);
                preparedStatement.setString(3, psi.email);
                preparedStatement.setInt(4, psi.courseId);
                preparedStatement.setString(5, psi.enrollementDate);
                preparedStatement.setBoolean(6, psi.courseApproval);
                preparedStatement.setBoolean(7, psi.isExpired);
                preparedStatement.setString(8, jsonString);

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
    public ArrayList<CourseInfo> courseEnrollmentUsingEmail(String email,int limit){
        ArrayList<CourseInfo> courses = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT ps.*, c.* FROM paid_student ps INNER JOIN courses c ON ps.courseId = c._id WHERE ps.email = ? AND ps.courseApproval = ? LIMIT ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setBoolean(2, true);
                preparedStatement.setInt(3, limit);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        try {
                            String _id = resultSet.getString("courseId");
                            String title = resultSet.getString("title");
                            String thumbnail = resultSet.getString("thumbmail");
                            int price = Integer.parseInt(resultSet.getString("price"));
                            boolean isReleased = Boolean.parseBoolean(resultSet.getString("isReleased"));
                            int discount = Integer.parseInt(resultSet.getString("discount"));;
                            String details = resultSet.getString("details");
                            String instructorName = resultSet.getString("instructorName");
                            int messagePort = resultSet.getInt("messagePort");
                            CourseInfo ci = new CourseInfo(_id,title,thumbnail,price,isReleased,discount,details,instructorName,messagePort);
                            courses.add(ci);
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
        return courses;
    }
    public ArrayList<CourseInfo> enrolledCourseForOverview(String email){
        ArrayList<CourseInfo> courses = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT ps.*, c.* FROM paid_student ps INNER JOIN courses c ON ps.courseId = c._id WHERE ps.email = ? LIMIT ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setInt(2, 2);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        try {
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
                            courses.add(ci);
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
        return courses;
    }
    public int totalCourseNumber(String email){
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT COUNT(*) AS row_count FROM paid_student ps INNER JOIN courses c ON ps.courseId = c._id WHERE ps.email = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);

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
    public ArrayList<PaidStudentInfo> getRequests(String courseId,int limit,boolean isApprove){
        ArrayList<PaidStudentInfo> psi = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM paid_student WHERE courseId = ? AND courseApproval = ? LIMIT ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,courseId);
                statement.setBoolean(2,isApprove);
                statement.setInt(3,limit);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String bkashNumber = resultSet.getString("bkashNumber");
                            String transectionId = resultSet.getString("transectionId");
                            System.out.println(transectionId);
                            String email = resultSet.getString("email");
                            String enrollementDate = resultSet.getString("enrollementDate");
                            Boolean courseApproval = resultSet.getBoolean("courseApproval");
                            Boolean isExpired = resultSet.getBoolean("isExpired");

                            PaidStudentInfo ps = new PaidStudentInfo(Integer.parseInt(_id),bkashNumber,transectionId,email,Integer.parseInt(courseId),enrollementDate,courseApproval,isExpired);
                            psi.add(ps);
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
        return psi;
    }
    public boolean updateApproval(String id){
        String updateSql = "UPDATE paid_student SET courseApproval = ? WHERE _id = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, id);
            int rowsAffected = preparedStatement.executeUpdate();
            connection.close();
            return (rowsAffected > 0);
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean declineRequest(String id){
        try {
            Connection connection = new DBConnections().getConnection();
            String query = "DELETE FROM paid_student WHERE _id = ?";
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
    public ArrayList<StudentRequest> studentRequestUsingTeacherMail(String email, int limit){
        ArrayList<StudentRequest> srs = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT ps.*, c.* FROM paid_student ps INNER JOIN courses c ON ps.courseId = c._id WHERE c.instructorMail = ? AND  ps.courseApproval = ? LIMIT ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setBoolean(2, false);
                preparedStatement.setInt(3, limit);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        try {
                            String _id = resultSet.getString("courseId");
                            String title = resultSet.getString("title");
                            String sEmail = resultSet.getString("email");
                            StudentRequest sr = new StudentRequest(_id,title,sEmail);
                            srs.add(sr);
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
        return srs;
    }
    public int totalApprovedStudentCount(String email){
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT COUNT(*) AS row_count FROM paid_student ps INNER JOIN courses c ON ps.courseId = c._id WHERE c.instructorMail = ? AND courseApproval = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setBoolean(2, true);

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
    public boolean banStudent(String id){
        String updateSql = "UPDATE paid_student SET isExpired = ? WHERE _id = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, id);
            int rowsAffected = preparedStatement.executeUpdate();
            connection.close();
            return (rowsAffected > 0);
        } catch (SQLException e) {
            return false;
        }
    }
}
