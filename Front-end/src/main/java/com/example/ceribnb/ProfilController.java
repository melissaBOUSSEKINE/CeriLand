package com.example.ceribnb;

import com.example.ceribnb.models.Object;
import com.example.ceribnb.models.Panier;
import com.example.ceribnb.models.User;
import com.example.ceribnb.services.ApiService;
import com.example.ceribnb.services.VarGlobal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfilController implements Initializable {

    @FXML
    private Text name;

    @FXML
    private Text address;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
        name.setText(VarGlobal.currentUser.getUsername());
        address.setText(VarGlobal.currentUser.getAddr());

    }

    public void initialize(URL url, ResourceBundle rb) {
        name.setText(VarGlobal.currentUser.getUsername());
        address.setText(VarGlobal.currentUser.getAddr());

    }

    public void setPanier(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("panier.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void commandSent(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandEnvoye.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
