package com.example.ceribnb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConnexionController {


    @FXML

    private Button retour;
    @FXML
    void setRetour(javafx.event.ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("role.fxml"));
            Scene scene = new Scene(root);
            // scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) retour.getScene().getWindow();
            window.setScene(scene);
           /* FXMLLoader loader = new FXMLLoader(getClass().getResource("role.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();*/

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
