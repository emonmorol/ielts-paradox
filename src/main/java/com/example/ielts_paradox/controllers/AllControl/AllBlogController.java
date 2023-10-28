package com.example.ielts_paradox.controllers.AllControl;

import com.example.ielts_paradox.controllers.cardControllers.BlogCardController;
import com.example.ielts_paradox.models.BlogInfo;
import com.example.ielts_paradox.utils.DBConnections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllBlogController implements Initializable {
    @FXML
    private VBox firstBox,secondBox;
    @FXML
    private HBox allBlgBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<BlogInfo> blogs = new DBConnections().getAllBlog();
        boolean isFirst = true;
        for(BlogInfo blog:blogs){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/cards/blogCard.fxml"));
                VBox paneee = fxmlLoader.load();
                BlogCardController bcc = fxmlLoader.getController();
                bcc.setData(blog);
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
