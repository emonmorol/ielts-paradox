package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.controllers.CourseDetailsController;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.models.CourseInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.font.ImageGraphicAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CourseOfferingCardController{
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label _id;

    @FXML
    private Label courseDetails;

    @FXML
    private Label courseTitle;

    @FXML
    private Label discountedPrice;

    @FXML
    private Label instructorName;

    @FXML
    private Label totalPrice;
    @FXML
    private ImageView courseBanner;
    @FXML
    private Rectangle rectangle;

    @FXML
    void seeDetailsHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/offeredCourseDetails.fxml"));
        root = fxmlLoader.load();
        CourseDetailsController cdc = fxmlLoader.getController();
        CourseInfo cf = new ForCourse().getCourseById(_id.getText());
        cdc.setDetailsInfo(cf,"1");
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void setData(CourseInfo course) throws FileNotFoundException {
        courseDetails.setText(course.details);
        courseTitle.setText(course.title);
        totalPrice.setText(String.valueOf(course.price)+"TK");
        int disPrice = course.price - (course.price * (course.discount)/100);
        discountedPrice.setText(String.valueOf(disPrice)+"TK");
        instructorName.setText(course.instructorName);
        _id.setText(course._id);

        if (course.thumbnail != null) {
            File imageFile = new File(course.thumbnail);
            try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
                Image img = new Image(fileInputStream);
                rectangle.setFill(new ImagePattern(img));
            } catch (FileNotFoundException e) {
                ErrorAlert.displayCustomAlert("Error Loading", "Image File Not Found: " + course.thumbnail);
            } catch (IOException e) {
                ErrorAlert.displayCustomAlert("Error Loading", "Error reading image file: " + e.getMessage());
            }
        } else {
            ErrorAlert.displayCustomAlert("Error Loading", "Image Path is NULL");
        }

    }

}
