/*
Author: Justin Kutscherousky
Date:5/25/2016
Company: Ark-La-Tex Color Lab Longview, TX 75601
Description: Simple JavaFX 8 + Hibernate 4.3.10+ MYSql 6.3 application that queries a table from a database on a local network
and alerts if a particular "job order number" is being used. 

*/

package com.altcolorlab.rollcall;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Roll Call");
        stage.setScene(scene);
        stage.show();
    }
  @Override
    public void stop(){
        System.exit(0);
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
