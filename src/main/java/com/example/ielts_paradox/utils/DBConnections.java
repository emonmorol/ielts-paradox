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

    public boolean validate(String userEmail,String userPass, boolean userType){
        String DB_QUERY = "SELECT * FROM USERS WHERE email = ? AND password = ? AND isTeacher = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            try (PreparedStatement prpStatement = connection.prepareStatement(DB_QUERY)) {
                prpStatement.setString(1,userEmail);
                prpStatement.setString(2,userPass);
                prpStatement.setBoolean(3,userType);
                try (ResultSet resultSet = prpStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try{
                            String email = resultSet.getString("email");
                            String fullname = resultSet.getString("fullname");
                            String contact_number = resultSet.getString("contact_number");
                            Boolean isTeacher = resultSet.getBoolean("isTeacher");
                            UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
                            user.setUser(new UserInfo(fullname,email,contact_number,isTeacher));
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                        return true;

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isUserExist(String email){
        String DB_QUERY = "SELECT * FROM USERS WHERE email = ?";
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement prpStatement = connection.prepareStatement(DB_QUERY);
            prpStatement.setString(1,email);

            ResultSet resultSet = prpStatement.executeQuery();
            if(resultSet.next()){

                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean createUser(UserInfo u){
        String DB_QUERY = "INSERT INTO users VALUES (?,?,?,?,?);";
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement prpStatement = connection.prepareStatement(DB_QUERY);
            prpStatement.setString(1,u.email);
            prpStatement.setString(2,u.fullName);
            prpStatement.setString(3,u.contactNumber);
            prpStatement.setString(4,u.password);
            prpStatement.setBoolean(5,u.isTeacher);
            int rowsAffected = prpStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<CourseInfo> getAllCourse(){
        ArrayList<CourseInfo> cf = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM courses";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
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
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
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

    public ArrayList<BlogInfo> getAllBlog(){
        ArrayList<BlogInfo> blogs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM blogs";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
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
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
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

    public ArrayList<StoryInfo> getAllStories(){
        ArrayList<StoryInfo> blogs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM success_stories";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String mainStory = resultSet.getString("mainStory");
                            String studentImage = resultSet.getString("studentImage");
                            String studentName = resultSet.getString("studentName");
                            String bandScore = resultSet.getString("bandScore");
                            StoryInfo b = new StoryInfo(_id,mainStory,studentImage,studentName,bandScore);
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

    public StoryInfo getStoryById(String id){
        StoryInfo ci;

        String DB_QUERY = "SELECT * FROM success_stories WHERE _id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DB_QUERY)) {
                preparedStatement.setInt(1, Integer.parseInt(id));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        try{
                            String _id = resultSet.getString("_id");
                            String mainStory = resultSet.getString("mainStory");
                            String studentImage = resultSet.getString("studentImage");
                            String studentName = resultSet.getString("studentName");
                            String bandScore = resultSet.getString("bandScore");
                            StoryInfo b = new StoryInfo(_id,mainStory,studentImage,studentName,bandScore);
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
    public boolean courseEnrollment(PaidStudentInfo psi){
        String insertQuery = "INSERT INTO paid_student (bkashNumber, transectionId, email, courseId, enrollementDate, courseApproval, isExpired) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<PaidStudentInfo> courseEnrollmentUsingEmail(String email){
        ArrayList<PaidStudentInfo> courses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM paid_student WHERE email = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        try {
                            int _id = Integer.parseInt(resultSet.getString("_id"));
                            String bkashNumber = resultSet.getString("bkashNumber");
                            String transectionId = resultSet.getString("transectionId");
                            String uemail = resultSet.getString("email");
                            int courseId = resultSet.getInt("courseId");
                            String enrollementDate = resultSet.getString("enrollementDate");
                            boolean courseApproval = resultSet.getBoolean("courseApproval");
                            boolean isExpired = resultSet.getBoolean("isExpired");

                            PaidStudentInfo b = new PaidStudentInfo(_id,bkashNumber,transectionId,uemail,courseId,enrollementDate,courseApproval,isExpired);
                            System.out.println();
                            courses.add(b);
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
}
