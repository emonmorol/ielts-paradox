package com.example.ielts_paradox.controllers.ExamPageController;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SeeResultAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.SocketNetworking.Exam.MultiThreadedSocketServer;
import com.example.ielts_paradox.SocketNetworking.Exam.SocketClient;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class StudentExamPageController implements Initializable {

    @FXML
    private TextField answerArea;
    @FXML
    private Label examTime;

    @FXML
    private Label timerLabel;
    @FXML
    private MFXButton reload;

    @FXML
    private Label title;

    @FXML
    private WebView webview;
    @FXML
    private MFXButton questionId;
    @FXML
    private MFXButton submitId;

    @FXML
    private MFXScrollPane sPane;

    @FXML
    private VBox vBox;
    @FXML
    private Stage stage;
    @FXML
    private Label isRunning;
    @FXML
    private Label isTimerRunning;

    private Scene scene;
    private Parent root;
    private static  int ADDITIONAL_MINUTES = 1;



    private Timeline timeline;
    private Duration duration;
    private KeyFrame keyFrame;
    static TestInfo in;
    public String id_;

    String meetUri;
    String studentSubmissionUri;
    String resultPaper;
    String questionPaperLink;
    String practiceQuestionPaperLink;
    String timeString2;
    String teacherMail;
    UserInfo ui = UserSingleTon.getInstance(new UserInfo()).getUser();

    @FXML
    void reloadHandler(ActionEvent event) {
        stopTimer();
        setData(new ForTest().getTestInfoById(id_));

    }
    private boolean toStop = false;
    public void setData(TestInfo ti){

        if(ti.isTaken){
            questionId.setDisable(true);
            submitId.setDisable(true);
            answerArea.setEditable(false);
            reload.setDisable(false);
            System.out.println();
        }
        System.out.println(ti.isTaken);
        this.teacherMail = ti.teacherMail;
        title.setText(ti.examModule+" Mock Test");
        in = ti;
        id_ = ti._id;

        if(ti.examDate != null){
            isTimerRunning.setText("1");
        }

        if(ti.meetLink != null){
            meetUri = ti.meetLink;
        }else{
            meetUri = null;
        }

        if(ti.questionLink != null){
            questionPaperLink = ti.questionLink;
        }else{
            questionPaperLink = null;
        }

        if(ti.practiceQuestionLink != null){
            practiceQuestionPaperLink = ti.practiceQuestionLink;
        }else{
            practiceQuestionPaperLink = null;
        }



        if(ti.resultLink != null){
            resultPaper = ti.resultLink;
        }else{
            resultPaper = null;
        }
        if(ti.examDate != null){
            timeString2 = ti.examDate;


            LocalDateTime cDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:a,MM/dd/yyyy");
            String timeString1 = cDate.format(formatter);
            System.out.println(timeString1);
            System.out.println(timeString2);
            LocalDateTime dateTime1 = parseTimeString(timeString1);
            LocalDateTime dateTime2 = parseTimeString(timeString2);
            if(!ti.isTaken){
                Instant instant1 = dateTime1.atZone(ZoneId.systemDefault()).toInstant();
                Instant instant2 = dateTime2.atZone(ZoneId.systemDefault()).toInstant();


                long secondsBetween = ChronoUnit.SECONDS.between(instant1, instant2);
                duration = Duration.seconds(secondsBetween);
                updateTimerLabel();

                keyFrame = new KeyFrame(Duration.seconds(1), event -> {
                    duration = duration.subtract(Duration.seconds(1));
                    updateTimerLabel();
                    if (duration.equals(Duration.ZERO)) {
                        if(!toStop){
                            toStop = true;
                            startTimer(ADDITIONAL_MINUTES);
                            timerLabel.setText("Exam Is Running");

                            timerLabel.setFont(new Font(20));
                            timerLabel.setTextFill(Color.BLUE);
                            isRunning.setText("1");
                            questionId.setDisable(false);
                            submitId.setDisable(false);
                            answerArea.setEditable(true);
                            reload.setDisable(true);
                            String text = "Your Scheduled "+in.examModule+" test is started.You are request to start the exam now.";
                            boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text, "Exam Started", in.studentMail, "System", "IELTS Paradox", in.examModule));
                            boolean isNoticeUpload1 = new ForNotices().uploadNotices(new NoticeInfo(text, "Exam Started", in.teacherMail, "System", "IELTS Paradox", in.examModule));


                        }else{
                            toStop = false;
                            timerLabel.setText("Exam Ended");
                            timerLabel.setFont(new Font(20));
                            timerLabel.setTextFill(Color.RED);
                            isRunning.setText("0");
                            questionId.setDisable(true);
                            submitId.setDisable(true);
                            answerArea.setEditable(false);
                            reload.setDisable(false);
                            stopTimer();
                            new ForTest().updateIsTaken(id_);
                            String text = "Your Scheduled "+in.examModule+" test is Ended. Wait for your result.";
                            boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text, "Exam Ended", in.studentMail, "System", "IELTS Paradox", in.examModule));
                            boolean isNoticeUpload1 = new ForNotices().uploadNotices(new NoticeInfo(text, "Exam Ended", in.teacherMail, "System", "IELTS Paradox", in.examModule));

                        }

                    }
                });

                timeline = new Timeline(keyFrame);
                timeline.setCycleCount((int) duration.toSeconds());
                timeline.play();
            }


        }
    }
    void setDefaultLink(){
        loadvideo("https://drive.google.com/file/d/1z97wcEoQkHjrJHlqqelDH9kzaKL5p3UK/view?usp=sharing");
    }

    @FXML
    void answerSubmit(ActionEvent event)  {
        String text = answerArea.getText();
        answerArea.clear();

        boolean isSubmitted = new ForTest().updateSubmissionLink(id_,text);
        if(isSubmitted){
            String text2 = "Student Updated the Paper for "+in.examModule+" test. Check the paper and update the result";
            boolean isNoticeUpload = new ForNotices().uploadNotices(new NoticeInfo(text2, "Paper Recieved", in.teacherMail, ui.email, ui.fullName, in.examModule));
            SuccessAlert.displayCustomAlert();
        }else{
            ErrorAlert.displayCustomAlert("Failed!","Something Went Wrong!\nTry Again!");
        }
    }

    @FXML
    void downloadQuestion(ActionEvent event) throws URISyntaxException, IOException {
        if(questionPaperLink==null){
            ErrorAlert.displayCustomAlert("Failed","Can't Open the link\nLink not set yet!");
        }else{
            loadvideo(questionPaperLink);
        }
    }

    @FXML
    void meetLink(ActionEvent event) throws URISyntaxException, IOException {
        if(meetUri == null){
            ErrorAlert.displayCustomAlert("Can't Open!", "Link Hasn't Set Yet!");
        }else{

            Desktop.getDesktop().browse(new URI(meetUri));
        }
    }

    @FXML
    void practiseQuestion(ActionEvent event) throws URISyntaxException, IOException {
        if(practiceQuestionPaperLink==null){
            ErrorAlert.displayCustomAlert("Failed","Can't Open the link\nLink not set yet!");
        }else{
            loadvideo(practiceQuestionPaperLink);
        }
    }

    @FXML
    void resultLink(ActionEvent event) {
        if(StudentExamPageController.in.resultLink == null){
            ErrorAlert.displayCustomAlert("Failed","Result Not Published\nYET");
        }else{
            SeeResultAlert.displayCustomAlert("Student Bandscore",StudentExamPageController.in.resultScore, StudentExamPageController.in.resultLink);
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
                }
            }
        }
    }


    public void loadvideo(String uri){
        webview.getEngine().load(uri);
    }
    @FXML
    void backButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/students/studentDashboard.fxml"));
        root = fxmlLoader.load();
        StudentDashboardController sdc =fxmlLoader.getController();
        sdc.onClick5(event);
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
            duration = Duration.ZERO;
            updateTimerLabel();
        }
    }

    private void startTimer(int minutes) {
        duration = Duration.minutes(minutes);
        updateTimerLabel();

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(minutes * 60);
        timeline.play();
    }

    private void updateTimerLabel() {
        if (duration.toMinutes() >= 60) {
            long totalSeconds = (long) duration.toSeconds();

            long days = totalSeconds / (24 * 3600);
            long hours = (totalSeconds % (24 * 3600)) / 3600;
            long minutes = (totalSeconds % 3600) / 60;
            long seconds = totalSeconds % 60;

            examTime.setText(String.format("%02dd %02dh %02dm %02ds", days, hours, minutes, seconds));
        } else {
            long minutes = (long) duration.toMinutes();
            long seconds = (long) (duration.toSeconds() % 60);

            examTime.setText(String.format("%02dm %02ds", minutes, seconds));
        }
    }

    private LocalDateTime parseTimeString(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:a,MM/dd/yyyy");
        return LocalDateTime.parse(timeString, formatter);
    }

    @FXML
    void openMesseneger(ActionEvent event) {
        if(!new ForChat().isRunning(55555)){
            new MultiThreadedSocketServer().startThreading(55555);
            new ForChat().updatePort(55555,true);
        }

        new SocketClient().runClient(55555,teacherMail,id_);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDefaultLink();
            if(isRunning.getText().equals("0")){
                questionId.setDisable(true);
                submitId.setDisable(true);
                answerArea.setEditable(false);
                reload.setDisable(false);
            }
            else{
                questionId.setDisable(false);
                submitId.setDisable(false);
                answerArea.setEditable(true);
                reload.setDisable(true);
            }

    }

}
