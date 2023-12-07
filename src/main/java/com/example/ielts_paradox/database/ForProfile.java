package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ForProfile {

    public boolean updateEmail(String oldMail, String newMail){
        UserInfo info = UserSingleTon.getInstance(new UserInfo()).getUser();

        String updateSql = "UPDATE users SET email = ? WHERE email = ?";

        String updateSql2 = "UPDATE paid_student SET email = ? WHERE email = ?";

        String updateSql3 = "UPDATE courses SET instructorMail = ? WHERE instructorMail = ?";
        String updateSql4 = "UPDATE success_stories SET teacherMail = ? WHERE teacherMail = ?";
        String updateSql5 = "UPDATE test_students SET teacherMail = ? WHERE teacherMail = ?";
        String updateSql6 = "UPDATE test_students SET studentMail = ? WHERE studentMail = ?";

        try {

            if(info.isTeacher){
                Connection connection = new DBConnections().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
                preparedStatement.setString(1, newMail);
                preparedStatement.setString(2, oldMail);
                int rowsAffected = preparedStatement.executeUpdate();

                PreparedStatement preparedStatement2 = connection.prepareStatement(updateSql3);
                preparedStatement2.setString(1, newMail);
                preparedStatement2.setString(2, oldMail);
                int rowsAffected2 = preparedStatement2.executeUpdate();

                PreparedStatement preparedStatement3 = connection.prepareStatement(updateSql4);
                preparedStatement3.setString(1, newMail);
                preparedStatement3.setString(2, oldMail);
                int rowsAffected3 = preparedStatement2.executeUpdate();

                PreparedStatement preparedStatement4 = connection.prepareStatement(updateSql5);
                preparedStatement4.setString(1, newMail);
                preparedStatement4.setString(2, oldMail);
                int rowsAffected4 = preparedStatement2.executeUpdate();

                return (rowsAffected > 0 && rowsAffected2 > 0 && rowsAffected3 > 0 && rowsAffected4 > 0);
            }else{
                Connection connection = new DBConnections().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
                preparedStatement.setString(1, newMail);
                preparedStatement.setString(2, oldMail);
                int rowsAffected = preparedStatement.executeUpdate();

                PreparedStatement preparedStatement2 = connection.prepareStatement(updateSql2);
                preparedStatement2.setString(1, newMail);
                preparedStatement2.setString(2, oldMail);
                int rowsAffected2 = preparedStatement2.executeUpdate();

                PreparedStatement preparedStatement3 = connection.prepareStatement(updateSql6);
                preparedStatement3.setString(1, newMail);
                preparedStatement3.setString(2, oldMail);
                int rowsAffected3 = preparedStatement2.executeUpdate();

                return (rowsAffected > 0 && rowsAffected2 > 0 && rowsAffected3 > 0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateName(String mail, String newname){
        String updateSql = "UPDATE users SET fullname = ? WHERE email = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, newname);
            preparedStatement.setString(2, mail);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected >0);
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean updateContact(String mail, String newContact){
        String updateSql = "UPDATE users SET contact_number = ? WHERE email = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, newContact);
            preparedStatement.setString(2, mail);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected > 0);
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean updatePassword(String mail, String newPassword){
        String updateSql = "UPDATE users SET password = ? WHERE email = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, mail);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected > 0);
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean updateBio(String mail, String newBio){
        String updateSql = "UPDATE users SET bio = ? WHERE email = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, newBio);
            preparedStatement.setString(2, mail);
            int rowsAffected = preparedStatement.executeUpdate();

            return (rowsAffected > 0);
        } catch (SQLException e) {
            return false;
        }
    }

}
