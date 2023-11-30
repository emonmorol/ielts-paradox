package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.TestInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ForTest {

    public ArrayList<TestInfo> teachersTests(String email, int limit){
        ArrayList<TestInfo> tis = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM test_students WHERE teacherMail = ? LIMIT ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,email);
                statement.setInt(2,limit);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            String _id= resultSet.getString("_id");
                            String enrollmentDate= resultSet.getString("enrollmentDate");
                            String examModule= resultSet.getString("examModule");
                            String studentMail= resultSet.getString("studentMail");
                            boolean isAccepted= resultSet.getBoolean("isAccepted");
                            boolean isTaken= resultSet.getBoolean("isTaken");
                            String transectionId= resultSet.getString("transectionId");
                            String bkashNumber= resultSet.getString("bkashNumber");
                            TestInfo ti = new TestInfo(_id,enrollmentDate,examModule,studentMail,transectionId,bkashNumber,isAccepted,isTaken);
//                            System.out.println(_id + " "+ examModule);
                            tis.add(ti);
                        }catch (SQLException e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tis;
    }
    public int takeTestCount(String teacherMail){
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT COUNT(*) AS row_count FROM test_students WHERE teacherMail = ? AND isTaken = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, teacherMail);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public ArrayList<TestInfo> getTestRequests(String type,int limit,boolean accepted){
        ArrayList<TestInfo> tis = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM test_students WHERE examModule = ? AND isAccepted = ? LIMIT ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,type);
                statement.setBoolean(2,accepted);
                statement.setInt(3,limit);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            String _id= resultSet.getString("_id");
                            String enrollmentDate= resultSet.getString("enrollmentDate");
                            String examModule= resultSet.getString("examModule");
                            String studentMail= resultSet.getString("studentMail");
                            boolean isAccepted= resultSet.getBoolean("isAccepted");
                            boolean isTaken= resultSet.getBoolean("isTaken");
                            String transectionId= resultSet.getString("transectionId");
                            String bkashNumber= resultSet.getString("bkashNumber");
                            TestInfo ti = new TestInfo(_id,enrollmentDate,examModule,studentMail,transectionId,bkashNumber,isAccepted,isTaken);
                            tis.add(ti);
                        }catch (SQLException e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tis;
    }
    public ArrayList<TestInfo> getApprovedStudents(String mail,int limit){
        ArrayList<TestInfo> tis = new ArrayList<>();
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM test_students WHERE teacherMail = ? AND isAccepted = ? LIMIT ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,mail);
                statement.setBoolean(2,true);
                statement.setInt(3,limit);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            String _id= resultSet.getString("_id");
                            String enrollmentDate= resultSet.getString("enrollmentDate");
                            String examModule= resultSet.getString("examModule");
                            String studentMail= resultSet.getString("studentMail");
                            boolean isAccepted= resultSet.getBoolean("isAccepted");
                            boolean isTaken= resultSet.getBoolean("isTaken");
                            String transectionId= resultSet.getString("transectionId");
                            String bkashNumber= resultSet.getString("bkashNumber");
                            TestInfo ti = new TestInfo(_id,enrollmentDate,examModule,studentMail,transectionId,bkashNumber,isAccepted,isTaken);
                            tis.add(ti);
                        }catch (SQLException e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tis;
    }

    public TestInfo getTestInfoById(String id){
        try{
            Connection connection = new DBConnections().getConnection();
            String sql = "SELECT * FROM test_students WHERE _id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        try{
                            String _id= resultSet.getString("_id");
                            String meetLink= resultSet.getString("meetLink");
                            String resultScore= resultSet.getString("resultScore");
                            String resultLink= resultSet.getString("resultLink");
                            String enrollmentDate= resultSet.getString("enrollmentDate");
                            String examDate= resultSet.getString("examDate");
                            String studentSubmissionLink= resultSet.getString("studentSubmissionLink");
                            String examModule= resultSet.getString("examModule");
                            String questionLink= resultSet.getString("questionLink");
                            String practiceQuestionLink= resultSet.getString("practiceQuestionLink");
                            String studentMail= resultSet.getString("studentMail");
                            String teacherMail= resultSet.getString("teacherMail");
                            boolean isAccepted= resultSet.getBoolean("isAccepted");
                            boolean isTaken= resultSet.getBoolean("isTaken");
                            String transectionId= resultSet.getString("transectionId");
                            String bkashNumber= resultSet.getString("bkashNumber");
                            TestInfo ti = new TestInfo(_id,meetLink,resultScore,resultLink,enrollmentDate,examDate,studentSubmissionLink,examModule,questionLink,practiceQuestionLink,studentMail,teacherMail,isAccepted,isTaken,transectionId,bkashNumber);
                            return ti;
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

    public boolean updateQuestionLink(String id,String link){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        String updateSql = "UPDATE test_students SET questionLink = ? WHERE _id = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, link);
            preparedStatement.setString(2, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updatePracticeQuestionLink(String id,String link){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        String updateSql = "UPDATE test_students SET practiceQuestionLink = ? WHERE _id = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, link);
            preparedStatement.setString(2, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateExamDate(String id,String date){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        String updateSql = "UPDATE test_students SET examDate = ? WHERE _id = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateMeetLink(String id,String link){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        String updateSql = "UPDATE test_students SET meetLink = ? WHERE _id = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, link);
            preparedStatement.setString(2, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateResult(String id,String score,String link){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        String updateSql = "UPDATE test_students SET resultScore = ?,resultLink = ? WHERE _id = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, score);
            preparedStatement.setString(2, link);
            preparedStatement.setString(3, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateTestApproval(String id){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();
        String updateSql = "UPDATE test_students SET isAccepted = ?,teacherMail = ? WHERE _id = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, info.email);
            preparedStatement.setString(3, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean declineRequest(String id){
        try {
            Connection connection = new DBConnections().getConnection();
            String query = "DELETE FROM test_students WHERE _id = ?";
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
