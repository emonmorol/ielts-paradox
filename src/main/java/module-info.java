module com.example.ielts_paradox {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ielts_paradox to javafx.fxml;
    opens com.example.ielts_paradox.utils to javafx.fxml;
    opens com.example.ielts_paradox.controllers to javafx.fxml;

    exports com.example.ielts_paradox;
    exports com.example.ielts_paradox.utils;
    exports com.example.ielts_paradox.controllers;

}