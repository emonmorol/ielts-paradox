package com.example.ielts_paradox.controllers.student;

import com.example.ielts_paradox.controllers.cardControllers.OverviewEnrolledCourseCardController;
import com.example.ielts_paradox.controllers.cardControllers.OverviewPopularCourseCardController;
import com.example.ielts_paradox.controllers.cardControllers.PopularBlogCardController;
import com.example.ielts_paradox.controllers.cardControllers.PopularStoryCardController;
import com.example.ielts_paradox.database.ForBlogs;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.StoryInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.LoadDashboardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    @FXML
    AnchorPane mainAnchor ;
    @FXML
    private VBox enrolledCourse;
    @FXML
    private VBox popularCourse;
    @FXML
    private VBox popularBlog;
    @FXML
    private VBox popularStory;
    @FXML
    private VBox upcomingExam;
    @FXML
    public void onClickSeeCourse(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/courses.fxml");
        mainAnchor.getChildren().add(panel);
    }

    @FXML
    public void onClickEnrollCourse(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/my_classes.fxml");
        mainAnchor.getChildren().add(panel);
    }

    @FXML
    void onClickBlogs(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/blogs.fxml");
        mainAnchor.getChildren().add(panel);
    }



    @FXML
    void onClickPopularCourses(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/courses.fxml");
        mainAnchor.getChildren().add(panel);
    }



    @FXML
    void onClickSuccessStories(ActionEvent event) {
        LoadDashboardPane ob = new LoadDashboardPane();
        AnchorPane panel = ob.getSidePane("/fxmls/students/pages/success_stories.fxml");
        mainAnchor.getChildren().add(panel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo u = user.getUser();
        ArrayList<CourseInfo> it = new ForEnrollment().courseEnrollmentUsingEmail(u.email,2);
        for (CourseInfo c: it){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/overviewEnrolledCourseCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                enrolledCourse.getChildren().add(paneee);
                OverviewEnrolledCourseCardController oeccc = fxmlLoader.getController();
                oeccc.setData(c);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        ArrayList<CourseInfo> it2 = new ForCourse().getAllCourse(0,2);
        for (CourseInfo c: it2){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/overviewPopularCourseCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                popularCourse.getChildren().add(paneee);
                OverviewPopularCourseCardController opccc = fxmlLoader.getController();
                opccc.setData(c);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<BlogInfo> it3 = new ForBlogs().getAllBlog(0,2);
        for (BlogInfo c: it3){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/popularBlogCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                popularBlog.getChildren().add(paneee);
                PopularBlogCardController pbcc = fxmlLoader.getController();
                pbcc.setData(c);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ArrayList<StoryInfo> it4 = new ForStories().getAllStories(0,2);
        for (StoryInfo c: it4){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/popularStoryCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                popularStory.getChildren().add(paneee);
                PopularStoryCardController pscc = fxmlLoader.getController();
                pscc.setData(c);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i<3;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/upcomingExamCard.fxml"));
            try {
                AnchorPane paneee = fxmlLoader.load();
                upcomingExam.getChildren().add(paneee);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}