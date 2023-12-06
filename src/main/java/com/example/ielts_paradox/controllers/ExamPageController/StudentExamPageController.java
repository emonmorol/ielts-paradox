package com.example.ielts_paradox.controllers.ExamPageController;

import com.example.ielts_paradox.Alerts.ErrorAlert;
import com.example.ielts_paradox.Alerts.SeeResultAlert;
import com.example.ielts_paradox.Alerts.SuccessAlert;
import com.example.ielts_paradox.controllers.student.StudentDashboardController;
import com.example.ielts_paradox.controllers.teacher.TeacherDashboardController;
import com.example.ielts_paradox.database.ForTest;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    private Hyperlink meetId;

    @FXML
    private Label points;

    @FXML
    private Hyperlink practiseId;

    @FXML
    private Hyperlink questionId;

    @FXML
    private MFXButton resultId;

    @FXML
    private MFXScrollPane sPane;

    @FXML
    private Label timerLabel;

    @FXML
    private VBox vBox;

    @FXML
    private Stage stage;

    private Scene scene;

    private Parent root;

    private static  int ADDITIONAL_MINUTES = 40;

    @FXML
    private TextArea writeMessage;

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

    private boolean toStop = false;
    public void setData(TestInfo ti){

        in = ti;
        id_ = ti._id;


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
                    }else{
                        toStop = false;
                        stopTimer();
                    }

                }
            });

            timeline = new Timeline(keyFrame);
            timeline.setCycleCount((int) duration.toSeconds());
            timeline.play();
        }
    }

    @FXML
    void answerSubmit(ActionEvent event)  {
        String text = answerArea.getText();
        boolean isSubmitted = new ForTest().updateSubmissionLink(id_,text);
        if(isSubmitted){
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
            Desktop.getDesktop().browse(new URI(questionPaperLink));
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
            Desktop.getDesktop().browse(new URI(practiceQuestionPaperLink));
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

    public void sentMessage(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i =1;i<=10;i++){
            FXMLLoader loder = new FXMLLoader(getClass().getResource("/fxmls/messages/mockTestIncomingCard.fxml"));
            FXMLLoader loder2 = new FXMLLoader(getClass().getResource("/fxmls/messages/mockTestOutgoingCard.fxml"));
            try {
                AnchorPane crd = loder.load();
                vBox.getChildren().add(crd);
                AnchorPane crd2 = loder2.load();
                vBox.getChildren().add(crd2);
                sPane.setVvalue(1.0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
