package com.example.ceribnb;

import com.example.ceribnb.models.Command;
import com.example.ceribnb.models.Object;
import com.example.ceribnb.models.vueModels.ObejctCard;
import com.example.ceribnb.services.ApiService;
import com.example.ceribnb.services.VarGlobal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CommandeValide implements Initializable {

    @FXML
    private GridPane cardGrid;

    void buildObjectCards(int nombreObjects, ArrayList<Object> objects, boolean showAddButton, boolean showdeleteButton,boolean showValRefButton){
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
            ObejctCard obejctCard = new ObejctCard(objects.get(i), imageHashMap.get(objects.get(i).getImgUrl()), false, false, false,  cardGrid);
            VarGlobal.addButton.setVisible(false);
            this.cardGrid.add(obejctCard.gethBox(), col, row);
            this.cardGrid.setPadding(new Insets(10));
            col++;
            if (col == this.cardGrid.getColumnCount()){
                col = 0;
                row++;
            }
        }
    }

    public void initialize(URL url, ResourceBundle rb) {



    }
}
