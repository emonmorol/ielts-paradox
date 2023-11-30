package com.example.ielts_paradox.controllers.cardControllers;

import com.example.ielts_paradox.controllers.MockTestController.TestRequestController;
import com.example.ielts_paradox.models.TestInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TeacherTestRequestCardController {

    @FXML
    private Label enrollmentDate;

    @FXML
    private Label logo;

    @FXML
    private Label testName;


    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;



    public Label getTestName() {
        return testName;
    }

    @FXML
    void goToTestRequestPage(ActionEvent event) throws IOException {
        System.out.println(testName.getText());
        if(Objects.equals(testName.getText(), "Speaking")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/testRequestTable.fxml"));
            root = fxmlLoader.load();
            TestRequestController stc = fxmlLoader.getController();
            stc.setData("Speaking","1");
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

        else if (Objects.equals(testName.getText(), "Reading")) {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxmls/teacher/mocktest/testRequestTable.fxml"));
            this.root = (Parent)fxmlLoader.load();
            TestRequestController ltc = fxmlLoader.getController();
            ltc.setData("Reading","1");
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else if (Objects.equals(testName.getText(), "Writing")){
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxmls/teacher/mocktest/testRequestTable.fxml"));
            this.root = (Parent)fxmlLoader.load();
            TestRequestController ltc = fxmlLoader.getController();
            ltc.setData("Writing","1");
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        else if (Objects.equals(testName.getText(), "Listening")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/teacher/mocktest/testRequestTable.fxml"));
            root = fxmlLoader.load();
            TestRequestController ltc = fxmlLoader.getController();
            ltc.setData("Listening","1");
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }


    }

    public void setData(TestInfo ti){
        enrollmentDate.setText(ti.enrollmentDate);
        testName.setText(ti.examModule);
        logo.setText(ti.examModule.charAt(0)+"");
    }


}





