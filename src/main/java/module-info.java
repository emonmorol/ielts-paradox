module com.example.ielts_paradox {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires MaterialFX;


    opens com.example.ielts_paradox to javafx.fxml;
    opens com.example.ielts_paradox.utils to javafx.fxml;
    opens com.example.ielts_paradox.controllers to javafx.fxml;
    opens com.example.ielts_paradox.controllers.AllControl to javafx.fxml;
    opens com.example.ielts_paradox.controllers.cardControllers to javafx.fxml;

    exports com.example.ielts_paradox;
    exports com.example.ielts_paradox.utils;
    exports com.example.ielts_paradox.controllers;
    exports com.example.ielts_paradox.controllers.AllControl;
    exports com.example.ielts_paradox.controllers.cardControllers;

}