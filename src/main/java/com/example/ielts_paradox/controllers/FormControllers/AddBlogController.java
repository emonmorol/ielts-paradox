package com.example.ielts_paradox.controllers.FormControllers;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForBlogs;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.models.StoryInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBlogController {

    @FXML
    private TextArea content;

    @FXML
    private MFXTextField image;

    @FXML
    private MFXTextField score;

    @FXML
    private MFXTextField title;

    @FXML
    public void uploadBlog(ActionEvent event) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d'-'MMMM'-'yyyy");
        String formattedDate = currentDate.format(formatter);

        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();


        BlogInfo si = new BlogInfo(title.getText(),formattedDate,info.fullName,content.getText(),"",score.getText());
        Boolean isUpload = new ForBlogs().uploadBlog(si);
        if(isUpload){
            SuccessAlert.displayCustomAlert();
        }else{
            ErrorAlert.displayCustomAlert("Upload Failed!","Something Went Wrong!\nTry Again!");
        }
    }
}
