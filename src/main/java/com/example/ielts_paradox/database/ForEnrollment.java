package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.PaidStudentInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean courseEnrollment(PaidStudentInfo psi){
        String insertQuery = "INSERT INTO paid_student (bkashNumber, transectionId, email, courseId, enrollementDate, courseApproval, isExpired) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, psi.bkashNumber);
                preparedStatement.setString(2, psi.enrollementDate);
                preparedStatement.setString(3, psi.email);
                preparedStatement.setInt(4, psi.courseId);
                preparedStatement.setString(5, psi.enrollementDate);
                preparedStatement.setBoolean(6, psi.courseApproval);
                preparedStatement.setBoolean(7, psi.isExpired);

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
    public ArrayList<CourseInfo> courseEnrollmentUsingEmail(String email,int limit){
        ArrayList<CourseInfo> courses = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT ps.*, c.* FROM paid_student ps INNER JOIN courses c ON ps.courseId = c._id WHERE ps.email = ? LIMIT ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setInt(2, limit);

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
                            CourseInfo ci = new CourseInfo(_id,title,thumbnail,price,isReleased,discount,details,instructorName);
                            courses.add(ci);
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
                            CourseInfo ci = new CourseInfo(_id,title,thumbnail,price,isReleased,discount,details,instructorName);
                            courses.add(ci);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
