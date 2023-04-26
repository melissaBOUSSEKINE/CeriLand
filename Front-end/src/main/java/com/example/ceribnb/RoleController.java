package com.example.ceribnb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RoleController implements Initializable {

    @FXML
    private Button button_hote;
    @FXML

    private Button button_voyageur;
    @FXML
    void connexionHote(javafx.event.ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("connexion.fxml"));
            Scene scene = new Scene(root);
            // scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) button_hote.getScene().getWindow();
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
    @FXML
    void connexionVoyageur(javafx.event.ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("connexion.fxml"));
            Scene scene = new Scene(root);
            // scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) button_voyageur.getScene().getWindow();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
