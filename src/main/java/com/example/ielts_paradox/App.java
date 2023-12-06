package com.example.ielts_paradox;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.database.ForCourseContent;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.CourseVideo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/login/login_page.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Image logo = new Image(getClass().getResource("/images/logo.png").toExternalForm());
            stage.setTitle("IELTS ParadOx");
            stage.getIcons().add(logo);
            stage.setScene(scene);
            stage.show();

//            ArrayList<CourseVideo> courses = new ArrayList<>();/* your list of CourseVideo objects */;
//            courses.add(new CourseVideo(1,"True/False/Not Given","https://www.youtube.com/embed/c_WFec8QJA4",false));
//            courses.add(new CourseVideo(2,"রিডিং লাইভ ক্লাস।। প্যাসেজ-৩","https://www.youtube.com/embed/WrE81RIErpI",false));
//            courses.add(new CourseVideo(3,"Completing sentences with correct ending","https://www.youtube.com/embed/jPB3odX-F9s",false));
//
//            Gson gson = new Gson();
//
//            JsonArray jsonArray = new JsonArray();
//            for (CourseVideo course : courses) {
//                JsonElement jsonElement = gson.toJsonTree(course);
//                jsonArray.add(jsonElement);
//            }
//
//            String jsonString = gson.toJson(jsonArray);
//            System.out.println(jsonString);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}