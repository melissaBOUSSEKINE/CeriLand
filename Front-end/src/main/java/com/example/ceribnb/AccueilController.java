package com.example.ceribnb;

import com.example.ceribnb.models.Object;
import com.example.ceribnb.models.vueModels.ObejctCard;
import com.example.ceribnb.services.ApiService;
import com.example.ceribnb.services.VarGlobal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
    private TextField searchTextFiledDate;
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
        this.buildObjectCards(VarGlobal.allObjects.size(), VarGlobal.allObjects);

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

        long startTime = System.currentTimeMillis();

//        final int[] row = {0};
//        final int[] col = {0};

        AtomicInteger row = new AtomicInteger();
        AtomicInteger col = new AtomicInteger();

        int numThreads = Runtime.getRuntime().availableProcessors(); // 获取可用的处理器核心数
        int objectsPerThread = (int) Math.ceil((double) nombreObjects / numThreads) + 300; // 每个线程处理的对象数量

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int t = 0; t < numThreads; t++) {
            int startIndex = t * objectsPerThread;
            int endIndex = Math.min(startIndex + objectsPerThread, nombreObjects);

            executorService.submit(() -> {
                for (int i = startIndex; i < endIndex; i++) {
                    Object object = objects.get(i);
                    ObejctCard obejctCard = new ObejctCard(object, imageHashMap.get(object.getImgUrl()), true, false, false, cardGrid);

                    synchronized (cardGrid) {
                        cardGrid.add(obejctCard.gethBox(), col.get(), row.get());
                        cardGrid.setPadding(new Insets(10));
                        col.getAndIncrement();
                        if (col.get() == cardGrid.getColumnCount()) {
                            col.set(0);
                            row.getAndIncrement();
                        }
                    }
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        List<Thread> threads = new ArrayList<>();
//
//        for (int t = 0; t < numThreads; t++) {
//            int startIndex = t * objectsPerThread;
//            int endIndex = Math.min(startIndex + objectsPerThread, nombreObjects);
//
//            Thread thread = new Thread(() -> {
//                for (int i = startIndex; i < endIndex; i++) {
//                    Object object = objects.get(i);
//                    ObejctCard obejctCard = new ObejctCard(object, imageHashMap.get(object.getImgUrl()), true, false, false, cardGrid);
//
//                    synchronized (cardGrid) {
//                        cardGrid.add(obejctCard.gethBox(), col[0], row[0]);
//                        cardGrid.setPadding(new Insets(10));
//                        col[0]++;
//                        if (col[0] == cardGrid.getColumnCount()) {
//                            col[0] = 0;
//                            row[0]++;
//                        }
//                    }
//                }
//            });
//
//            threads.add(thread);
//            thread.start();
//        }
//
//        // 等待所有线程执行完成
//        for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime + " milliseconds");
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

    @FXML
    void searchByDate(ActionEvent event) {
        System.out.println(this.searchTextFiledDate.getText());
        String searchKey = this.searchTextFiledDate.getText();
        if(searchKey.equals("")){
            this.cardGrid.getChildren().clear();
            this.msgResult.setText("");
            this.buildObjectCards(1000, VarGlobal.allObjects);
        } else {
            ArrayList<Object> results = ApiService.getObjectsByDate(searchKey);
            this.msgResult.setText("Has " + results.size() + " results");
            this.cardGrid.getChildren().clear();
            this.buildObjectCards(results.size(), results);
        }
    }

}
