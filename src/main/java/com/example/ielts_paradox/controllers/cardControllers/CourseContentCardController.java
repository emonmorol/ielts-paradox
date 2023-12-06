package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.CourseContentController;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.CourseVideo;
import javafx.fxml.FXML;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class CourseContentCardController {
    @FXML
    private FontAwesomeIconView lock_icon;

    @FXML
    private FontAwesomeIconView status_icon;

    @FXML
    private Label title;
    @FXML
    private Label contentId;
    @FXML
    private Label videoId;

    CourseInfo ci;
    String p;
    CourseContentController oldCCC;
    CourseVideo cvss;
    @FXML
    void playVideoHandler(MouseEvent event) {
        if(oldCCC.validate(cvss._id)){
            oldCCC.loadVideo(cvss.videoURI);
            oldCCC.runningVideoId = cvss._id;
            boolean isWatched = oldCCC.updateVideoWatching(Integer.parseInt(ci._id), cvss._id);
            if(isWatched){
                lock_icon.setText("\uF00C");
            }
        }
    }

    public void setData(CourseInfo ci, String p, CourseContentController oldCCC, CourseVideo cv){
        this.ci = ci;
        this.p = p;
        this.oldCCC = oldCCC;
        title.setText(cv._id + ". "+cv.videoTitle);
        boolean isTrue = cv.isWatched;
        if(isTrue)
            lock_icon.setText("\uF00C");
        cvss = cv;
    }
}
