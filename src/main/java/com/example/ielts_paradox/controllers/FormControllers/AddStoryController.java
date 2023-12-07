package com.example.ielts_paradox.controllers.FormControllers;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.database.ForStories;
import com.example.ielts_paradox.models.StoryInfo;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;

public class AddStoryController {
    @FXML
    private MFXTextField bandScore;

    @FXML
    private TextArea story;

    @FXML
    private MFXTextField studentName;
    private File selectedFile;

    @FXML
    public void chooseImage(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Routine Image");
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    @FXML
    public void addStoryHandler(ActionEvent event) {
        saveImage(selectedFile, studentName.getText()+".jpg");
        StoryInfo si = new StoryInfo(story.getText(),studentName.getText()+".jpg",studentName.getText(),bandScore.getText());
        Boolean isUpload = new ForStories().uploadStory(si);
        if(isUpload){
            SuccessAlert.displayCustomAlert();
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
