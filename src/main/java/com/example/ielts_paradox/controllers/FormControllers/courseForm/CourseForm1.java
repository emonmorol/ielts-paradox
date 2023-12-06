package com.example.ielts_paradox.controllers.FormControllers.courseForm;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class CourseForm1 {


    @FXML
    private TextArea description;

    @FXML
    private MFXTextField discount;

    @FXML
    private MFXTextField price;

    @FXML
    private MFXTextField title;


    private File selectedFile;

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
    void saveButton(ActionEvent event) {
        System.out.println(description.getText());
        System.out.println(discount.getText());
        System.out.println(price.getText());
        System.out.println(title.getText());
        if (selectedFile != null) {
            try {

                FileInputStream fr = new FileInputStream(selectedFile);

                FileOutputStream fw = new FileOutputStream( "moral.png");
                fw.write(fr.read());

                fw.flush();
                fw.close();
                fr.close();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {

            System.out.println("No file selected");
        }
    }
}
