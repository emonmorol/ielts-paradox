package com.example.ielts_paradox.controllers.FormControllers.courseForm;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;


public class CourseForm1 {


    @FXML
    private TextArea description;

    @FXML
    private MFXTextField discount;

    @FXML
    private MFXTextField price;

    @FXML
    private MFXTextField title;

    @FXML
    private MFXTextField messagePort;




    private File selectedFile;
    private File selectedFile2;

    @FXML
    void chooseBanner(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Banner Image");
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    @FXML
    void chooseRoutine(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Routine Image");
        selectedFile2 = fileChooser.showOpenDialog(null);

        if (selectedFile2 != null) {

            System.out.println("Selected file: " + selectedFile2.getAbsolutePath());
        }

    }

    @FXML
    void saveButton(ActionEvent event) {
        AddCourseController.title = title.getText();
        AddCourseController.discount = Integer.parseInt(discount.getText());
        AddCourseController.price = Integer.parseInt(price.getText());
        AddCourseController.details = description.getText();
        AddCourseController.messagePort = Integer.parseInt(messagePort.getText());


        saveImage(selectedFile, title.getText()+"_banner.jpg");
        saveImage(selectedFile2, title.getText()+"_routine.jpg");

        AddCourseController.thumbnail = title.getText()+"_banner.jpg";
        AddCourseController.routine = title.getText()+"_routine.jpg";
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
