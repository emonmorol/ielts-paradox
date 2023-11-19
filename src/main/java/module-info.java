module com.example.ielts_paradox {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires MaterialFX;
    requires com.google.gson;
    requires javafx.web;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.apache.pdfbox;

    opens com.example.ielts_paradox to javafx.fxml;
    opens com.example.ielts_paradox.utils to javafx.fxml;
    opens com.example.ielts_paradox.controllers to javafx.fxml;
    opens com.example.ielts_paradox.controllers.AllControl to javafx.fxml;
    opens com.example.ielts_paradox.controllers.cardControllers to javafx.fxml;
    opens com.example.ielts_paradox.controllers.FormControllers to javafx.fxml;
    opens com.example.ielts_paradox.controllers.teacher to javafx.fxml;
    opens com.example.ielts_paradox.controllers.student to javafx.fxml;
    opens com.example.ielts_paradox.controllers.alertController to javafx.fxml;
    opens com.example.ielts_paradox.Alerts to javafx.fxml;

    exports com.example.ielts_paradox;
    exports com.example.ielts_paradox.utils;
    exports com.example.ielts_paradox.controllers;
    exports com.example.ielts_paradox.controllers.AllControl;
    exports com.example.ielts_paradox.controllers.cardControllers;
    exports com.example.ielts_paradox.models;
    exports com.example.ielts_paradox.controllers.FormControllers;
    exports com.example.ielts_paradox.controllers.teacher;
    exports com.example.ielts_paradox.controllers.student;
    exports com.example.ielts_paradox.controllers.alertController;
    exports com.example.ielts_paradox.Alerts;

}