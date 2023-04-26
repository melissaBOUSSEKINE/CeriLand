package com.example.ceribnb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.ceribnb.TestController;
public class TestApplication extends Application {

    GridPane imageGrid = new GridPane();

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
       // loader.setRoot(imageGrid);
       // loader.setController(new TestController());
        Parent root = loader.load();
        TestController controller = loader.getController();
        controller.loadImages();

        // Set up the scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My Image List");

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
