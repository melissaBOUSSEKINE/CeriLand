package com.example.ceribnb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AccueilController implements Initializable {

    @FXML
    private GridPane imageGrid;
    @FXML
    private Button button_connexion;



    @FXML
    void choixRole(javafx.event.ActionEvent event) {

        try {
           /* Parent root = FXMLLoader.load(getClass().getResource("role.fxml"));
            Scene scene = new Scene(root);
            // scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) button_connexion.getScene().getWindow();
            window.setScene(scene);*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("role.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
    @Override
   /* public void initialize(URL url, ResourceBundle rb) {
        File folder = new File("C:\\Users\\bouss\\OneDrive\\Images\\Captures d’écran");
        File[] files = folder.listFiles();

        int row = 0;
        int col = 0;

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".png")) {
                try {
                    // create an ImageView for the image
                    ImageView imageView = new ImageView(new Image(file.toURI().toString()));
                    imageView.setFitWidth(133);
                    imageView.setFitHeight(92);
                    // create a label for the title
                    // label = new Label(file.getName());
                   // label.setAlignment(Pos.CENTER);
                    // add the image and title to a VBox
                    VBox vBox = new VBox(imageView);
                    vBox.setAlignment(Pos.CENTER);
                    // add the VBox to the grid pane
                    imageGrid.add(vBox, col, row);
                    col++;
                    // if we reach the end of a row, move to the next row and reset the column count
                    if (col == imageGrid.getColumnCount()) {
                        col = 0;
                        row++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    public void initialize(URL url, ResourceBundle rb) {
        File folder = new File("C:\\Users\\bouss\\OneDrive\\Images\\Captures d’écran");
        File[] files = folder.listFiles();

        int row = 0;
        int col = 0;

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".png")) {
                try {
                    // create an ImageView for the image
                    ImageView imageView = new ImageView(new Image(file.toURI().toString()));
                    imageView.setFitWidth(133);
                    imageView.setFitHeight(92);
                    // create a label for the title
                    // label = new Label(file.getName());
                    // label.setAlignment(Pos.CENTER);
                    // add the image and title to a VBox
                    VBox vBox = new VBox(imageView);
                    vBox.setAlignment(Pos.CENTER);
                    // add the VBox to the grid pane
                    imageGrid.add(vBox, col, row);
                    col++;
                    // if we reach the end of a row, move to the next row and reset the column count
                    if (col == imageGrid.getColumnCount()) {
                        col = 0;
                        row++;
                    }
                    // add an event handler to open a new window when the image is clicked
                    imageView.setOnMouseClicked(e -> {
                        try {
                            // create a new stage and set the scene
                            Stage stage = new Stage();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            // get the controller and set the image for the new window
                            TestController controller = loader.getController();
                            controller.setImage(imageView.getImage(), file);
                            // show the new window
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
