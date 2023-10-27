package com.example.ielts_paradox.controllers.cardControllers;

import javafx.fxml.FXML;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.input.MouseEvent;

public class CourseContentCardController {
    @FXML
    private FontAwesomeIconView lock_icon;

    @FXML
    private FontAwesomeIconView status_icon;
    @FXML
    void playVideoHandler(MouseEvent event) {
        if(lock_icon.getText()!="\uF00C")
            lock_icon.setText("\uF00C");
    }
}
