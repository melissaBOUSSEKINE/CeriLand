package com.example.ceribnb;

import com.example.ceribnb.models.Object;
import com.example.ceribnb.models.Panier;
import com.example.ceribnb.models.User;
import com.example.ceribnb.services.ApiService;
import com.example.ceribnb.services.VarGlobal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.ceribnb.services.VarGlobal.ObjetProp;
import static com.example.ceribnb.services.VarGlobal.demandObj;

public class ProfilController implements Initializable {

    @FXML
    private Text name;

    @FXML
    private Text address;
    @FXML
    private AnchorPane anchor;

    private User currentUser;

    public void initialize(URL url, ResourceBundle rb) {
        name.setText(VarGlobal.currentUser.getUsername());
        address.setText(VarGlobal.currentUser.getAddr());

        System.out.print(VarGlobal.currentUser.getRole());

        if(VarGlobal.currentUser.getRole().equals("owner")){

            this.anchor.getChildren().add(ObjetProp);
            AnchorPane.setRightAnchor(ObjetProp, 350.0);
           AnchorPane.setTopAnchor(ObjetProp, 220.0);
           System.out.println("heloo guys");

           // demandObj
            this.anchor.getChildren().add(demandObj);
            AnchorPane.setRightAnchor(demandObj, 180.0);
            AnchorPane.setTopAnchor(demandObj, 220.0);
            System.out.println("hello guys");


            VarGlobal.ObjetProp.setVisible(true);

            ObjetProp.setOnAction(e -> {
                showMyObject();
            });

            VarGlobal.demandObj.setVisible(true);

            demandObj.setOnAction(e -> {
                showDemand();
            });

        }

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

    public void showMyObject(){
        System.out.println("ownerrrrr");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("objProposé.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void showDemand(){
        //je vais m'emmer
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("demandeObj.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void commandvald(){
        //je vais m'emmer
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("commandevalide.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
