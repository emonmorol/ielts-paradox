package com.example.ielts_paradox.utils;

import com.example.ielts_paradox.models.*;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.sql.*;
import java.util.ArrayList;

public class DBConnections {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ielts_paradox?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "emon&&MYSQL";

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return connection;
    }
}
