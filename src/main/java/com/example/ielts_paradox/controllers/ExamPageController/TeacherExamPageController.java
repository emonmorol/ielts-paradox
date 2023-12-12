package com.example.ielts_paradox.controllers.ExamPageController;

import com.example.ielts_paradox.Alerts.*;
import com.example.ielts_paradox.SocketNetworking.Exam.MultiThreadedSocketServer;
import com.example.ielts_paradox.SocketNetworking.Exam.SocketClient;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.database.ForChat;
import com.example.ielts_paradox.database.ForNotices;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.NoticeInfo;
import com.example.ielts_paradox.models.TestInfo;
import com.example.ielts_paradox.models.UserInfo;
import com.example.ielts_paradox.singletons.UserSingleTon;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String dateFormate;
    @FXML
    private String selectedAmPm;
    @FXML
    private String selectedHour;
    @FXML
    private String selectedMinute;

    @FXML
    private WebView webview;
    @FXML
    private Label title;

    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;

    String meetUri;
    String studentSubmissionUri;
    String resultPaper;
    String studentMail;
    UserInfo ui = UserSingleTon.getInstance(new UserInfo()).getUser();


    @FXML
    private String[] hour = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    @FXML
    private String[] minute = {
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59", "00"
    };

    @FXML
    private String[] ap = {"AM", "PM"};
    static TestInfo in;

    @FXML
    void reloadHandler(ActionEvent event) {
        setData(new ForTest().getTestInfoById(id_));

    }

    public void setData(TestInfo ti) {
        this.studentMail = ti.studentMail;
        title.setText(ti.examModule+" Mock Test");
        in = ti;
        id_ = ti._id;
        if (ti.examDate != null) {
            String[] dt = ti.examDate.split(",");
            String[] tme = dt[0].split(":");
            hourBox.getSelectionModel().select(tme[0]);
            minuteBox.getSelectionModel().select(tme[1]);
            amPm.getSelectionModel().select(tme[2]);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            System.out.println(dt[1]);
            datePicker.setValue(LocalDate.parse(dt[1], formatter));

            selectedHour = tme[0];
            selectedMinute = tme[1];
            selectedAmPm = tme[2];
            dateFormate = dt[1];
        } else {
            hourBox.getSelectionModel().select("00");
            minuteBox.getSelectionModel().select("00");
            amPm.getSelectionModel().select("AM");
            datePicker.setValue(LocalDate.now());
            selectedHour = "00";
            selectedMinute = "00";
            selectedAmPm = "AM";
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedDate = localDate.format(formatter);
            System.out.println(formattedDate);
            datePicker.setValue(LocalDate.parse(formattedDate, formatter));
            dateFormate = formattedDate;
        }

        if (ti.meetLink != null) {
            meetUri = ti.meetLink;
        } else {
            meetUri = null;
        }

        if (ti.studentSubmissionLink != null) {
            studentSubmissionUri = ti.studentSubmissionLink;
        } else {
            studentSubmissionUri = null;
        }

        if (ti.resultLink != null) {
            resultPaper = ti.resultLink;
        } else {
            resultPaper = null;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDefaultLink();
        hourBox.getItems().addAll(hour);
        amPm.getItems().addAll(ap);
        setDropdownSize(minuteBox, 50);
        minuteBox.getItems().addAll(minute);

    }


    @FXML
    void openMesseneger(ActionEvent event) {
        if(!new ForChat().isRunning(55555)){
            new MultiThreadedSocketServer().startThreading(55555);
            new ForChat().updatePort(55555,true);
        }

        new SocketClient().runClient(55555,studentMail,id_);
    }



    @FXML
    void seeExamPaper(ActionEvent event) {
        if(studentSubmissionUri == null){
            ErrorAlert.displayCustomAlert("Can't Open!", "Link Hasn't Set Yet!");
        }else{
            loadvideo(studentSubmissionUri);
        }
    }
    @FXML
    void externalBrowserLink(ActionEvent event) {
        if (webview != null && webview.getEngine() != null && webview.getEngine().getLocation() != null) {
            String currentUrl = webview.getEngine().getLocation();
            if (currentUrl != null && !currentUrl.isEmpty()) {
                try {
                    Desktop.getDesktop().browse(new URI(currentUrl));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                    // Handle the exception as needed
                }
            }
        }
    }
    void setDefaultLink(){
        loadvideo("https://drive.google.com/file/d/1z97wcEoQkHjrJHlqqelDH9kzaKL5p3UK/view?usp=sharing");
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
        boolean isUpdated = FormAlert.displayCustomAlert("Enter Updated Meet Link Here!",id_);
        setData(new ForTest().getTestInfoById(id_));
        if (isUpdated) {
            String text = "I wanted to inform you of an important update regarding the meeting link for your upcoming "+in.examModule+" test. The previous link has been updated";
            boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text, "Meet Link Updated!", in.studentMail, ui.email, ui.fullName, in.examModule));
        }
    }

    @FXML
    void resultEdit(ActionEvent event) {
        TeacherResultEditAlert.displayCustomAlert(id_);
        setData(new ForTest().getTestInfoById(id_));
        String text = "I am pleased to inform you that the results for the recent "+in.examModule+" test have been published. You can now access your individual result in Exam Page.";
        boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text, "Result Published", in.studentMail, ui.email, ui.fullName, in.examModule));

    }

    @FXML
    void practiseQuestion(ActionEvent event) {
        boolean isUpdated = FormAlert.displayCustomAlert("Enter Practice Question Link Here!",id_);
        setData(new ForTest().getTestInfoById(id_));
        if (isUpdated) {
            String text = "I wanted to inform you of an important update regarding the Practice Question for your upcoming "+in.examModule+" test. The previous link has been updated";
            boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text, "Practice Question Updated!", in.studentMail, ui.email, ui.fullName, in.examModule));
        }

    }

    @FXML
    void uploadQuestion(ActionEvent event) {
        boolean isUpdated = FormAlert.displayCustomAlert("Enter Question Link Here!",id_);
        setData(new ForTest().getTestInfoById(id_));
        if (isUpdated) {
            String text = "I wanted to inform you of an important update regarding the Exam Question for your upcoming "+in.examModule+" test. The Question is already uploaded. Wait till the start of the exam.";
            boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text, "Question Updated!", in.studentMail, ui.email, ui.fullName, in.examModule));
        }
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
    void setTimeAndDate(ActionEvent event) {
        String eDate = selectedHour+":"+selectedMinute+":"+selectedAmPm+","+dateFormate;
        System.out.println(eDate);
        boolean isUpdated = new ForTest().updateExamDate(id_,eDate);
        if(isUpdated){
            String text = "I wanted to inform you of an important update regarding the Exam Schedule for your upcoming "+in.examModule+" test exam will start at "+eDate+".\nExam time is set.\nBe prepared";
            boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text, "Exam Schedule Updated!", in.studentMail, ui.email, ui.fullName, in.examModule));
            SuccessAlert.displayCustomAlert();
        }
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

    public void loadvideo(String uri){
        webview.getEngine().load(uri);
    }
}
