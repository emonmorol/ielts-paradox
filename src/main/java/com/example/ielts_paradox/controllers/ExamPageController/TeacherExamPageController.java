package com.example.ielts_paradox.controllers.ExamPageController;

import com.example.ielts_paradox.Alerts.*;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.TestInfo;
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
    String id_;
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

    String meetUri;
    String studentSubmissionUri;
    String resultPaper;




    @FXML
    private String[] hour = {"01","02","03","04","05","06","07","08","09","10","11","12"};

    @FXML
    private String[] minute = {
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"
    };

    @FXML
    private  String[] ap ={"AM","PM"};
    static TestInfo in;

    public void setData(TestInfo ti){
        in = ti;
        id_ = ti._id;
        if(ti.examDate != null && ti.examDate.length() > 2){
            String[] dt = ti.examDate.split(":");
            hourBox.getSelectionModel().select(dt[0]);
            minuteBox.getSelectionModel().select(dt[1]);
            amPm.getSelectionModel().select(dt[2]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            datePicker.setValue(LocalDate.parse(dt[3],formatter));
        }else{
            hourBox.getSelectionModel().select("00");
            minuteBox.getSelectionModel().select("00");
            amPm.getSelectionModel().select("AM");
            datePicker.setValue(LocalDate.now());
        }

        if(ti.meetLink != null && ti.meetLink.length() > 2){
            meetUri = ti.meetLink;
        }else{
            meetUri = null;
        }

        if(ti.studentSubmissionLink != null && ti.studentSubmissionLink.length() > 2){
            studentSubmissionUri = ti.studentSubmissionLink;
        }else{
            studentSubmissionUri = null;
        }

        if(ti.resultLink != null && ti.resultLink.length() > 2){
            resultPaper = ti.resultLink;
        }else{
            resultPaper = null;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hourBox.getItems().addAll(hour);
        amPm.getItems().addAll(ap);

        // Set custom size for minuteBox dropdown
        setDropdownSize(minuteBox, 50);
        minuteBox.getItems().addAll(minute);
    }

    @FXML
    void sentMessage(ActionEvent event) {
        String wm = writeMessage.getText();
    }



    @FXML
    void backButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxmls/teacher/mocktest/mockTestTable.fxml"));
        this.root = (Parent)fxmlLoader.load();
//        MockTestTableController mttc = fxmlLoader.getController();
        this.scene = new Scene(this.root);
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.stage.setScene(this.scene);
        this.stage.show();
    }
    @FXML
    void dashboardHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/teacherDashboard.fxml"));
        root = fxmlLoader.load();
        TeacherDashboardController tdc = fxmlLoader.getController();
        tdc.onClick5(event);
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void meetEdit(ActionEvent event) {
        FormAlert.displayCustomAlert("Enter Updated Meet Link Here!",id_);
        setData(new ForTest().getTestInfoById(id_));
    }

    @FXML
    void resultEdit(ActionEvent event) {
        TeacherResultEditAlert.displayCustomAlert(id_);
        setData(new ForTest().getTestInfoById(id_));
    }

    @FXML
    void practiseQuestion(ActionEvent event) {
        FormAlert.displayCustomAlert("Enter Practice Question Link Here!",id_);
        setData(new ForTest().getTestInfoById(id_));

    }

    @FXML
    void uploadQuestion(ActionEvent event) {
        FormAlert.displayCustomAlert("Enter Question Link Here!",id_);
        setData(new ForTest().getTestInfoById(id_));
    }


    @FXML
    void seeResult(ActionEvent event) {
        SeeResultAlert.displayCustomAlert("Student Bandscore",TeacherExamPageController.in.resultScore, TeacherExamPageController.in.resultLink);
    }


    @FXML
    void openMeetLink(ActionEvent event) throws URISyntaxException, IOException {
        if(meetUri == null){
            ErrorAlert.displayCustomAlert("Can't Open!", "Link Hasn't Set Yet!");
        }else{
            Desktop.getDesktop().browse(new URI(meetUri));
        }
    }
    @FXML
    void seeExamPaper(ActionEvent event) throws URISyntaxException, IOException {
        if(meetUri == null){
            ErrorAlert.displayCustomAlert("Can't Open!", "Link Hasn't Set Yet!");
        }else{
            Desktop.getDesktop().browse(new URI(studentSubmissionUri));
        }
    }

    @FXML
    void setTimeAndDate(ActionEvent event) {
        String eDate = selectedHour+":"+selectedMinute+":"+selectedAmPm+":"+dateFormate;
        boolean isUpdated = new ForTest().updateExamDate(id_,eDate);
        if(isUpdated)
            SuccessAlert.displayCustomAlert();
        else
            ErrorAlert.displayCustomAlert("Failed","Can't Update the date\nTry Again!");
        setData(new ForTest().getTestInfoById(id_));
    }
    @FXML
    void getDate(ActionEvent event) {
        LocalDate myDate = datePicker.getValue();
        dateFormate = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    @FXML
    void getAmPm(ActionEvent event) {
        selectedAmPm = amPm.getValue();
    }

    @FXML
    void getHour(ActionEvent event) {
        selectedHour = hourBox.getValue();
    }

    @FXML
    void getMinute(ActionEvent event) {
        selectedMinute = minuteBox.getValue();
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
