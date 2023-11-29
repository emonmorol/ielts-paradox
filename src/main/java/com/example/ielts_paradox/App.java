package com.example.ielts_paradox;
import com.example.ielts_paradox.database.ForTest;
import com.example.ielts_paradox.models.TestInfo;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/login/login_page.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Image logo = new Image(getClass().getResource("/images/logo.png").toExternalForm());
            stage.setTitle("IELTS ParadOx");
            stage.getIcons().add(logo);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}