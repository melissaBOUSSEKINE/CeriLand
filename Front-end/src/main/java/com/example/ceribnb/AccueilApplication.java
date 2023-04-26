package com.example.ceribnb;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.ceribnb.TestController;
public class AccueilApplication extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("accueil.fxml"));
        Parent root = loader.load();
        AccueilController controller = loader.getController();


        // Set up the scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My Ceri");

        // Show the stage
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
