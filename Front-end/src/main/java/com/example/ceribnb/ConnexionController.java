package com.example.ceribnb;

import com.example.ceribnb.services.ApiService;
import com.example.ceribnb.services.VarGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConnexionController {


//    @FXML
//    private Button retour;


    @FXML
    private Button connect;

    @FXML
    private PasswordField mdp;

    @FXML
    private TextField username;

    @FXML
    void functionLogin(ActionEvent event) {
        try {
            //String username = this.username.getText();
           // String password = this.mdp.getText();

            String username = "Paula Hamilton";
            String password = "O3hga6fp6LnIjnY";

            System.out.println(username + " " + password);
            VarGlobal.currentUser = ApiService.login(username, password);
            if(VarGlobal.currentUser.getId() != null) {
                System.out.println(VarGlobal.currentUser);
                VarGlobal.currentUserNameText.setText("Bienvenue , " + VarGlobal.currentUser.getUsername() + " !");
                VarGlobal.loginBtn.setVisible(false);
                VarGlobal.logoutBtn.setVisible(true);
                VarGlobal.profil.setVisible(true);
                Stage stage = (Stage)connect.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Connection échouée! Veulliez vérifier votre username ou password! ");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
