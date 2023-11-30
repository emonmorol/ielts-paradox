package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.database.ForEnrollment;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.PaidStudentInfo;
import com.example.ielts_paradox.models.TestInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckoutController {
    @FXML
    private MFXTextField bkashNumber;

    @FXML
    private Label courseTitle;

    @FXML
    private MFXTextField courseTitleField;

    @FXML
    private Label discountedPrice;

    @FXML
    private MFXButton enrollementButton;

    @FXML
    private MFXTextField studentEmailField;

    @FXML
    private Label totalPrice;

    @FXML
    private MFXTextField transectionId;

    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;
    public static CourseInfo ci ;

    private boolean isCourse;

    @FXML
    public void backButtonHandler(ActionEvent e) throws IOException {
        if(isCourse){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/pages/offeredCourseDetails.fxml"));
            root = fxmlLoader.load();
            CourseDetailsController cdc = fxmlLoader.getController();
            cdc.setDetailsInfo(ci,"1");
            scene = new Scene(root);

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
            root = fxmlLoader.load();
            StudentDashboardController sdc = fxmlLoader.getController();

            scene = new Scene(root);
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            sdc.onClick5(e);
        }

    }
    public void setData(CourseInfo cf){
        isCourse = true;
        ci = cf;
        UserSingleTon ins = UserSingleTon.getInstance(new UserInfo());
        UserInfo user = ins.getUser();
        boolean isEnrolled = new ForEnrollment().validate(user.email,cf._id);
        if(isEnrolled){
            enrollementButton.setDisable(true);
            enrollementButton.setText("ALREADY ENROLLED");
        }
        studentEmailField.setText(user.email);
        courseTitleField.setText(ci.title);
        courseTitleField.setFloatingText("Course Title");
        courseTitle.setText(ci.title);
    }

    public void setData(String module,String price){
        isCourse = false;
        UserSingleTon ins = UserSingleTon.getInstance(new UserInfo());
        UserInfo user = ins.getUser();

        studentEmailField.setText(user.email);
        courseTitleField.setText(module);
        discountedPrice.setText(price+".0TK");
        totalPrice.setText(price+".0TK");
        courseTitleField.setFloatingText("Exam Module");
        courseTitle.setText(module);
    }
    @FXML
    public void enrollmentHandler(ActionEvent event){
        UserSingleTon ins = UserSingleTon.getInstance(new UserInfo());
        UserInfo user = ins.getUser();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        if(isCourse){
            PaidStudentInfo psi = new PaidStudentInfo(bkashNumber.getText(),transectionId.getText(),studentEmailField.getText(),Integer.parseInt(CheckoutController.ci._id),formattedDateTime,false,false);
            boolean isComplete = new ForEnrollment().courseEnrollment(psi);
            if(isComplete){
                SuccessAlert.displayCustomAlert();
                enrollementButton.setDisable(true);
                enrollementButton.setText("ALREADY ENROLLED");
            }
            else{
                ErrorAlert.displayCustomAlert("Error","Fill up the form with appropriete info!");
            }
        }else{
            TestInfo ti = new TestInfo("0",formattedDateTime,courseTitle.getText(),user.email,transectionId.getText(),bkashNumber.getText(),false,false);
            boolean isComplete = new ForTest().testEnrollment(ti);
            if(isComplete){
                SuccessAlert.displayCustomAlert();
                enrollementButton.setDisable(true);
                enrollementButton.setText("ENROLLED");
            }
            else{
                ErrorAlert.displayCustomAlert("Error","Fill up the form with appropriete info!");
            }
        }


    }


}
