package com.example.ielts_paradox.database;

import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.utils.DBConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForChat {
    public boolean isRunning(int portName){
        String DB_QUERY = "SELECT * FROM port_history WHERE port_name = ?";
        try{
            Connection connection = new DBConnections().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DB_QUERY)) {
                preparedStatement.setInt(1, portName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try{
                            boolean is = resultSet.getBoolean("isRunning");
                            return is;
                        }catch (SQLException e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void updatePort(int portName,boolean value){
        String updateSql = "UPDATE port_history SET isRunning = ? WHERE port_name = ?";

        try {
            Connection connection = new DBConnections().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setBoolean(1, value);
            preparedStatement.setInt(2, portName);
            int rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
