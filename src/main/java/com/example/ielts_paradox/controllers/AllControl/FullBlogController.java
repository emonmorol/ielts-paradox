package com.example.ielts_paradox.controllers.AllControl;

import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.controllers.cardControllers.BlogCardController;
import com.example.ielts_paradox.database.ForBlogs;
import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FullBlogController implements Initializable {
    @FXML
    private Label content;

    @FXML
    private Label date;

    @FXML
    private Label instructor;

    @FXML
    private Label points;

    @FXML
    private Label score;

    @FXML
    private Label title;

    @FXML
    private VBox allBlogBox;
    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<BlogInfo> blogs = new ForBlogs().getAllBlog(0,100);
        for(BlogInfo blog:blogs){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/blogCard.fxml"));
                VBox paneee = fxmlLoader.load();
                BlogCardController bcc = fxmlLoader.getController();
                bcc.setData(blog);
                allBlogBox.getChildren().add(paneee);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    @FXML
    void backButtonHandler(ActionEvent event) throws IOException {
        UserSingleTon ins = UserSingleTon.getInstance(new UserInfo());
        UserInfo user = ins.getUser();
        if(user.isTeacher){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/teacherDashboard.fxml"));
            root = fxmlLoader.load();
            TeacherDashboardController tdc =fxmlLoader.getController();
            if(points.getText()=="0")
                tdc.onClick7(event);
            else
                tdc.onClickOverview(event);
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc =fxmlLoader.getController();
            if(points.getText()=="0")
                sdc.onClick7(event);
            else
                sdc.onClickOverview(event);
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        }
        stage.show();
    }
    public void setData(BlogInfo blog,String p){
        title.setText(blog.title);
        instructor.setText(blog.publisherName);
        content.setText(blog.content);
        date.setText(blog.date);
        score.setText("Band Score: "+blog.bandScore);
        points.setText(p);
    }

}
