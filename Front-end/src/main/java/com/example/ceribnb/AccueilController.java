package com.example.ceribnb;

import com.example.ceribnb.models.*;
import com.example.ceribnb.models.Object;
import com.example.ceribnb.models.vueModels.ObejctCard;
import com.example.ceribnb.services.ApiService;
import com.example.ceribnb.services.VarGlobal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AccueilController implements Initializable {

    @FXML
    private AnchorPane acceuil;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane cardGrid;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTextFiled;

    @FXML
    private Text msgResult;

    public boolean isButtonVisible;

    void login() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
    @FXML
    void setProfil() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    void logout() {
        VarGlobal.currentUser = null;
        VarGlobal.logoutBtn.setVisible(false);
        VarGlobal.currentUserNameText.setVisible(false);
        VarGlobal.loginBtn.setVisible(true);
        VarGlobal.profil.setVisible(false);
    }

    public void initialize(URL url, ResourceBundle rb) {
        // Add the login button to the acceuil AnchorPane

        this.acceuil.getChildren().add(VarGlobal.loginBtn);
        AnchorPane.setRightAnchor(VarGlobal.loginBtn, 60.0);
        AnchorPane.setTopAnchor(VarGlobal.loginBtn, 20.0);

        // Add the logout button to the acceuil AnchorPane and hide it initially

        this.acceuil.getChildren().add(VarGlobal.logoutBtn);
        AnchorPane.setRightAnchor(VarGlobal.logoutBtn, 60.0);
        AnchorPane.setTopAnchor(VarGlobal.logoutBtn, 20.0);

        VarGlobal.logoutBtn.setVisible(false);

        // Add the profil button to the acceuil AnchorPane and hide it initially

        this.acceuil.getChildren().add(VarGlobal.profil);
        AnchorPane.setRightAnchor(VarGlobal.profil, 60.0);
        AnchorPane.setTopAnchor(VarGlobal.profil, 50.0);

        VarGlobal.profil.setVisible(false);




        // Add the current user name text to the acceuil AnchorPane

        this.acceuil.getChildren().add(VarGlobal.currentUserNameText);
        AnchorPane.setRightAnchor(VarGlobal.currentUserNameText, 175.0);
        AnchorPane.setTopAnchor(VarGlobal.currentUserNameText, 25.0);

        VarGlobal.loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                login();
            }
        });
        // Set up the login and logout button event handlers

        VarGlobal.profil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setProfil();
            }
        });

        VarGlobal.logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                logout();
            }
        });

        // Call an API service method to retrieve data from the server
        ApiService.getAllObjects();
        // Create a gray border for the scroll pane
        BorderStroke borderStroke = new BorderStroke(Color.GRAY,
                BorderStrokeStyle.SOLID, null, new BorderWidths(2));

        // 使用Border类创建一个边框对象
        Border border = new Border(borderStroke);

        // Set the border for the scroll pane
        // 为ScrollPane设置边框

        this.scrollPane.setBorder(border);

        // Call a method to build object cards and display them in the scroll pane
        this.buildObjectCards(1000, VarGlobal.allObjects);

    }

    void buildObjectCards(int nombreObjects, ArrayList<Object> objects){
        File folder = new File("..\\..\\images");
        File[] files = folder.listFiles();

        HashMap<String, Image> imageHashMap = new HashMap<>();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".jpg")) {
                String relativePath = "";
                String absolutPath = "";
                try {
                    Path filePath = Paths.get(file.getCanonicalPath());
                    Path currentPath = Paths.get("").toAbsolutePath();
                    Path relative = currentPath.relativize(filePath);
                    relativePath = relative.toString();
                    absolutPath = filePath.toString();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Image image = new Image(absolutPath);
                imageHashMap.put(relativePath, image);
            }
        }

        int row = 0;
        int col = 0;

        for (int i = 0; i < nombreObjects; i++) {
            ObejctCard obejctCard = new ObejctCard(objects.get(i), imageHashMap.get(objects.get(i).getImgUrl()), true,false,false, cardGrid);

            this.cardGrid.add(obejctCard.gethBox(), col, row);
            this.cardGrid.setPadding(new Insets(10));
            col++;
            if (col == this.cardGrid.getColumnCount()){
                col = 0;
                row++;
            }
        }
    }

    @FXML
    void searchFunction(ActionEvent event) {
        System.out.println(this.searchTextFiled.getText());
        String searchKey = this.searchTextFiled.getText();
        if(searchKey.equals("")){
            this.cardGrid.getChildren().clear();
            this.msgResult.setText("");
            this.buildObjectCards(1000, VarGlobal.allObjects);
        } else {
            ArrayList<Object> results = ApiService.getObjectsByTitle(searchKey);
            this.msgResult.setText("Has " + results.size() + " results");
            this.cardGrid.getChildren().clear();
            this.buildObjectCards(results.size(), results);
        }
    }

}
