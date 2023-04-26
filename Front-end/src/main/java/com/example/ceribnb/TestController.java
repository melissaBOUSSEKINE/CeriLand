package com.example.ceribnb;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private GridPane imageGrid;
    @FXML
    private BorderPane rootPane;

    @FXML
    private HBox navbar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navbar = new HBox(); // initialize the navbar object
        loadImages(); // call loadImages() method here or somewhere else where navbar has been initialized
    }

    public void loadImages() {
        File folder = new File("C:\\Users\\bouss\\OneDrive\\Images\\Captures d’écran"); // Replace with the path to your folder
        File[] imageFiles = folder.listFiles((dir, name) -> name.endsWith(".png")); // Replace ".jpg" with the extension of your images

        int col = 0;
        int row = 0;
        ImageView imageView;
        for (File file : imageFiles) {
            Image image = new Image(file.toURI().toString());
            imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            imageGrid.add(imageView, col, row);

            // Add a label for the title
            Label titleLabel = new Label("Title");
            imageGrid.add(titleLabel, col, row + 1);

            col++;
            if (col == 4) {
                col = 0;
                row += 2;
            }
        }

        // Add some links to the navbar
        Label homeLink = new Label("Home");
        Label aboutLink = new Label("About");
        Label contactLink = new Label("Contact");
        navbar.getChildren().addAll(homeLink, aboutLink, contactLink);
    }



    public void setImage(Image image, File file) {
        try {
            // Create a new ImageView object
            ImageView imageView = new ImageView();

            // Set the image as the ImageView's image
            imageView.setImage(image);

            // Set the ImageView's fit width and height to preserve aspect ratio
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(300);
            imageView.setFitHeight(300);

            // Add the ImageView to the root pane
            rootPane.getChildren().add(imageView);

        } catch (Exception e) {
            // Handle any exceptions that occur while loading the image
            e.printStackTrace();
        }
    }


}
