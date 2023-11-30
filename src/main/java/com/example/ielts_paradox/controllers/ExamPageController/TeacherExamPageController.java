package com.example.ielts_paradox.controllers.ExamPageController;

import com.example.ielts_paradox.Alerts.Alert2;
import com.example.ielts_paradox.Alerts.SeeResultAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.Alerts.TeacherResultEditAlert;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.MockTestController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TeacherExamPageController implements Initializable {


    @FXML
    private ComboBox<String> amPm;

    @FXML
    private ComboBox<String> hourBox;

    @FXML
    private ComboBox<String> minuteBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Hyperlink meetLink;

    @FXML
    private Hyperlink studentExamPaper;
    @FXML
    private TextArea question;
    @FXML
    private String dateFormate;
    @FXML
    private String selectedAmPm;
    @FXML
    private String selectedHour;
    @FXML
    private String selectedMinute;
    @FXML
    private TextArea writeMessage;

    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;





    @FXML
    private String[] hour = {"1","2","3","4","5","6","7","8","9","10","11","12"};

    @FXML
    private String[] minute = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"
    };

    @FXML
    private  String[] ap ={"AM","PM"};





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hourBox.getItems().addAll(hour);
        amPm.getItems().addAll(ap);

        // Set custom size for minuteBox dropdown
        setDropdownSize(minuteBox, 50);
        minuteBox.getItems().addAll(minute);

        hourBox.getSelectionModel().select("00");
        minuteBox.getSelectionModel().select("00");
        amPm.getSelectionModel().select("AM");
    }

    @FXML
    void sentMessage(ActionEvent event) {
        String wm = writeMessage.getText();
    }



    @FXML
    void backButtonHandler(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/teacherDashboard.fxml"));
            root = fxmlLoader.load();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        stage.show();
    }


    @FXML
    void meetEdit(ActionEvent event) {
        Alert2.displayCustomAlert("Enter Updated Link Meet Here!");
    }


    @FXML
    void resultEdit(ActionEvent event) {
        TeacherResultEditAlert.displayCustomAlert();

    }

    @FXML
    void practiseQuestion(ActionEvent event) {
        Alert2.displayCustomAlert("Enter Practise Question Link Here!");

    }


    @FXML
    void uploadQuestion(ActionEvent event) {
        Alert2.displayCustomAlert("Enter Question Link Here!");

    }

    @FXML
    void seeResult(ActionEvent event) {
        SeeResultAlert.displayCustomAlert("Student Bandscore","9");

    }

    @FXML
    void openMeetLink(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1OEipG7fBV-N3csrsQwfEbSGNiQsw33x6/view?usp=sharing"));

    }
    @FXML
    void seeExamPaper(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1OEipG7fBV-N3csrsQwfEbSGNiQsw33x6/view?usp=sharing"));

    }


    @FXML
    void getDate(ActionEvent event) {
        LocalDate myDate = datePicker.getValue();
         dateFormate = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        System.out.println("Selected Date: " +myDate.toString());

    }

    @FXML
    void getAmPm(ActionEvent event) {
         selectedAmPm = amPm.getValue();
        System.out.println("Selected AM/PM: " + selectedAmPm);
    }

    @FXML
    void getHour(ActionEvent event) {
        selectedHour = hourBox.getValue();
        System.out.println("Selected Hour: " + selectedHour);
    }

    @FXML
    void getMinute(ActionEvent event) {
        selectedMinute = minuteBox.getValue();
        System.out.println("Selected Minute: " + selectedMinute);
    }

    @FXML
    void setTimeAndDate(ActionEvent event) {
        System.out.println("Selected Date: " + dateFormate);
        System.out.println("Selected AM/PM: " + selectedAmPm);
        System.out.println("Selected Hour: " + selectedHour);
        System.out.println("Selected Minute: " + selectedMinute);
        SuccessAlert.displayCustomAlert();

    }

    private void setDropdownSize(ComboBox<String> comboBox, double maxHeight) {
        ListView<String> listView = getListView(comboBox);

        if (listView != null) {
            listView.setPrefHeight(maxHeight);
            listView.setMaxHeight(maxHeight);
        }
    }

    private ListView<String> getListView(ComboBox<String> comboBox) {
        for (javafx.scene.Node node : comboBox.getChildrenUnmodifiable()) {
            if (node instanceof ListView) {
                return (ListView<String>) node;
            }
        }
        return null;
    }
}
