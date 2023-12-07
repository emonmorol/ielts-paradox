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
import javafx.stage.FileChooser;

import java.io.*;
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
    private File selectedFile;

    @FXML
    void chooseBanner(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Routine Image");
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

    }

    @FXML
    public void uploadBlog(ActionEvent event) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d'-'MMMM'-'yyyy");
        String formattedDate = currentDate.format(formatter);

        UserSingleTon user = UserSingleTon.getInstance(new UserInfo());
        UserInfo info = user.getUser();
        BlogInfo si;
        if(selectedFile!=null){
            saveImage(selectedFile, title.getText()+"_banner.jpg");
            si = new BlogInfo(title.getText(),formattedDate,info.fullName,content.getText(),title.getText()+"_banner.jpg",score.getText());
        }else{
            si = new BlogInfo(title.getText(),formattedDate,info.fullName,content.getText(),null,score.getText());
        }

        Boolean isUpload = new ForBlogs().uploadBlog(si);
        if(isUpload){
            SuccessAlert.displayCustomAlert();
            title.clear();
            score.clear();
            content.clear();
        }else{
            ErrorAlert.displayCustomAlert("Upload Failed!","Something Went Wrong!\nTry Again!");
        }
    }
    private void saveImage(File sourceFile, String destinationFileName) {
        if (sourceFile != null) {
            try (FileInputStream fr = new FileInputStream(sourceFile);
                 FileOutputStream fw = new FileOutputStream(destinationFileName);
                 BufferedInputStream bufferedInput = new BufferedInputStream(fr);
                 BufferedOutputStream bufferedOutput = new BufferedOutputStream(fw)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                    bufferedOutput.write(buffer, 0, bytesRead);
                }

                System.out.println("File saved: " + destinationFileName);

            } catch (IOException e) {
                e.printStackTrace(); // Consider logging or displaying a more user-friendly error message
            }
        } else {
            System.out.println("No file selected");
        }
    }
}
