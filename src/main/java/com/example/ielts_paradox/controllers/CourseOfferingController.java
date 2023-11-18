package com.example.ielts_paradox.controllers;

import com.example.ielts_paradox.controllers.cardControllers.CourseOfferingCardController;
import com.example.ielts_paradox.database.ForCourse;
import com.example.ielts_paradox.models.CourseInfo;
import com.example.ielts_paradox.models.Faq;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseOfferingController implements Initializable {

    @FXML
    private VBox firstBox,secondBox;
    public static ArrayList<CourseInfo> courses = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String [] c1_features = {"Lorem Ipsum is simply dummy text of the printing","Lorem Ipsum is simply dummy text of the printing","Lorem Ipsum is simply dummy text of the printing","Lorem Ipsum is simply dummy text of the printing"};
//        String [] c1_point = {"Lorem Ipsum","Lorem Ipsum","the printing","simply dummy"};
//        ArrayList<Faq> qa = new ArrayList<>();
//        qa.add(new Faq("Lorem Ipsum is simply dummy text of the printing","Lorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printing"));
//        qa.add(new Faq("Lorem Ipsum is simply dummy text of the printing","Lorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printing"));
//        qa.add(new Faq("Lorem Ipsum is simply dummy text of the printing","Lorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printing"));
//        qa.add(new Faq("Lorem Ipsum is simply dummy text of the printing","Lorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printing"));
//        qa.add(new Faq("Lorem Ipsum is simply dummy text of the printing","Lorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printingLorem Ipsum is simply dummy text of the printing"));
//
//        CourseInfo c1 = new CourseInfo("1","Live Online Ielts Course",c1_features,"/images/live-course.png",5000,true,15,c1_features,qa,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",c1_point,"Rimon Morol");
//        CourseInfo c2 = new CourseInfo("2","Lorem Ipsum is simply dummy text of the printing",c1_features,"/images/live-course.png",7000,true,15,c1_features,qa,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",c1_point,"Emon Morol");
//        CourseInfo c3 = new CourseInfo("3","Writing online course",c1_features,"/images/live-course.png",3000,true,15,c1_features,qa,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",c1_point,"Abu Bakar");
//        CourseInfo c4 = new CourseInfo("4","Reading online course",c1_features,"/images/live-course.png",3000,true,10,c1_features,qa,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",c1_point,"Samiur Rahman Omlan");
//        CourseInfo c5 = new CourseInfo("5","Speaking online course",c1_features,"/images/live-course.png",8000,true,15,c1_features,qa,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",c1_point,"Emon Morol");
//        CourseInfo c6 = new CourseInfo("6","Listening online course",c1_features,"/images/live-course.png",2000,true,15,c1_features,qa,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",c1_point,"Abu Bakar");
//
//
//        courses.add(c1);
//        courses.add(c2);
//        courses.add(c3);
//        courses.add(c4);
//        courses.add(c5);
//        courses.add(c6);
        courses = new ForCourse().getAllCourse(0,100);


        boolean isFirst = true;
        for(CourseInfo i:courses){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/courseOfferingCard.fxml"));


                VBox paneee = fxmlLoader.load();
                CourseOfferingCardController cocd = fxmlLoader.getController();
                cocd.setData(i);
                if(isFirst){
                    firstBox.getChildren().add(paneee);
                    isFirst = false;
                }else{
                    secondBox.getChildren().add(paneee);
                    isFirst= true;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
