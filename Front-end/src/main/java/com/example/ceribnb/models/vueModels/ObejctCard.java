package com.example.ceribnb.models.vueModels;
import com.example.ceribnb.models.Command;
import com.example.ceribnb.models.Object;
import com.example.ceribnb.models.Response;
import com.example.ceribnb.services.ApiService;
import com.example.ceribnb.services.VarGlobal;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ObejctCard {

    private HBox hBox;
    private GridPane cardGrid;

    public ObejctCard(Object object, Image image, boolean showAddButton, boolean showDeleteButton,boolean showValRefButton, GridPane cardGrid){

        //showAddButton = true;
        this.cardGrid = cardGrid;
        System.out.println("Id: " + object.getId());

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(230);
        imageView.setFitHeight(180);
        imageView.setEffect(dropShadow);


            imageView.setOnMouseClicked(event -> {
                //CommentController commentController = new CommentController();
                VarGlobal.objetId =  object.getId();
                System.out.println("izannnn  " + VarGlobal.objetId);
               // commentController.refreshCommentList();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ceribnb/comment.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();


                    System.out.println("melii"+VarGlobal.objetId);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            });

        Tooltip tooltip = new Tooltip("Click here to add and see all the comments");
        Tooltip.install(imageView, tooltip);

        long timeStampStart = (long) Double.parseDouble(object.getDateDispoStart()) * 1000;
        Date dateStart = new Date(timeStampStart);
        SimpleDateFormat sdfStart = new SimpleDateFormat("yyyy/MM/dd");
        String dateStartString = sdfStart.format(dateStart);
//        System.out.println("Date dispo start : " + dateStartString);

        long timeStampEnd = (long) Double.parseDouble(object.getDateDispoEnd()) * 1000;
        Date dateEnd = new Date(timeStampEnd);
        SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy/MM/dd");
        String dateEndString = sdfEnd.format(dateEnd);
//        System.out.println("Date dispo end : " + dateEndString);

        Text title = new Text(object.getTitle());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Text labelDate = new Text("Date disponible: ");
        labelDate.setFill(Color.GRAY);

        Text dateDispo = new Text(dateStartString + " - " + dateEndString);
        Rectangle background = new Rectangle();
        background.setWidth(dateDispo.getLayoutBounds().getWidth() + 20);
        background.setHeight(dateDispo.getLayoutBounds().getHeight() + 10);
        background.setFill(Color.LIGHTGRAY);
        background.setArcWidth(50);
        background.setArcHeight(50);

        StackPane stackPaneDateDispo = new StackPane();
        stackPaneDateDispo.getChildren().addAll(background, dateDispo);

        Text labelPrix = new Text("Prix: ");
        labelPrix.setFill(Color.GRAY);

        Text prix = new Text(object.getPrix() + " €");
        prix.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        prix.setFill(Color.RED);

        HBox hBoxPrix = new HBox();
        hBoxPrix.getChildren().addAll(labelPrix, prix);

        TextFlow textFlowTitle = new TextFlow();
        textFlowTitle.setLayoutX(1);
        textFlowTitle.setLayoutY(1);

        textFlowTitle.setPrefWidth(250);
        textFlowTitle.getChildren().addAll(title);

       // Button addButton = new Button("Ajout dans le panier");

       // Button addButton = VarGlobal.addButton;

        Button addButton = new Button(VarGlobal.addButton.getText());
        addButton.setOnAction(VarGlobal.addButton.getOnAction());

        Button deleteButton = new Button(VarGlobal.deleteButton.getText());
        deleteButton.setOnAction(VarGlobal.deleteButton.getOnAction());

        Button validBtn = new Button(VarGlobal.valider.getText());
        deleteButton.setOnAction(VarGlobal.valider.getOnAction());

        Button refusBtn = new Button(VarGlobal.refuser.getText());
        deleteButton.setOnAction(VarGlobal.refuser.getOnAction());

        if (showAddButton) {
            addButton.setVisible(true);
            deleteButton.setVisible(false);
        } else if (showDeleteButton) {
            addButton.setVisible(false);
            deleteButton.setVisible(true);
        } else {
            addButton.setVisible(false);
            deleteButton.setVisible(false);
        }

        System.out.println("show is "+showValRefButton);
        if(showValRefButton){
            validBtn.setVisible(true);
            refusBtn.setVisible(true);

        }else{
            validBtn.setVisible(false);
            refusBtn.setVisible(false);
        }


       // deleteButton.setVisible(false);

        validBtn.setOnAction(e -> {
            validDemande(object);
            System.out.println("valider");
        });

        refusBtn.setOnAction(e -> {
            refusDemande(object);
            System.out.println("refuser");
        });


        addButton.setOnAction(e -> {
            onClickAddButton(object);

        });

        deleteButton.setOnAction(e -> {
            deleteObject(object , VarGlobal.currentUser.getId());
            System.out.println("delete success");
        });

        VBox vBoxInfo = new VBox(10);
        VBox.setMargin(textFlowTitle, new Insets(10));
        vBoxInfo.getChildren().add(textFlowTitle);
        vBoxInfo.getChildren().add(labelDate);
        vBoxInfo.getChildren().add(stackPaneDateDispo);
        VBox.setMargin(hBoxPrix, new Insets(10));
        vBoxInfo.getChildren().add(hBoxPrix);

        VBox.setMargin(addButton, new Insets(0,10,10,50));
        vBoxInfo.getChildren().add(addButton);

        VBox.setMargin(deleteButton, new Insets(0,10,10,50));
        vBoxInfo.getChildren().add(deleteButton);

        VBox.setMargin(validBtn, new Insets(0,10,10,50));
        vBoxInfo.getChildren().add(validBtn);

        VBox.setMargin(refusBtn, new Insets(10,10,10,50));
        vBoxInfo.getChildren().add(refusBtn);

        this.hBox = new HBox(10);
        this.hBox.getChildren().addAll(imageView, vBoxInfo);
        this.hBox.setAlignment(Pos.CENTER_LEFT);

        BorderStroke borderStroke = new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, null, new BorderWidths(1));
        Border border = new Border(borderStroke);

        this.hBox.setBorder(border);
        this.hBox.setPadding(new Insets(10));
        this.hBox.setSpacing(10);

    }

    void onClickAddButton(Object object) {
        if(VarGlobal.currentUser == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez connectez-vous avant d'ajouter l'objet dans le panier! ");
            alert.showAndWait();
        } else {
            System.out.println(object.getId());
            System.out.println(VarGlobal.currentUser.getId());
            Response res = ApiService.addObjectIntoPanier(object.getId(), VarGlobal.currentUser.getId());
            Alert alert = new Alert(res.getErrorCode().equals("0") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(res.getErrorMsg());
            alert.showAndWait();
        }
    }

    void deleteObject(Object obj, String id) {
        System.out.println(obj.getId());
        System.out.println(VarGlobal.currentUser.getId());

        Response res = ApiService.removeObjectFromPanier(obj.getId(), VarGlobal.currentUser.getId());

        if (res.getErrorCode().equals("0")) {
            this.cardGrid.getChildren().remove(this.hBox);
        }
        Alert alert = new Alert(res.getErrorCode().equals("0") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(res.getErrorMsg());
        alert.showAndWait();
    }

    public HBox gethBox() {
        return hBox;
    }

    public void validDemande(Object object) {
        ArrayList<Command> commands = ApiService.getCommanderByObjectId(object.getId());
        Response response = null;
        for (Command cmd : commands) {
            System.out.println(cmd.getCommanderId());
            response = ApiService.valideCommand(VarGlobal.currentUser.getId(), object.getId(), cmd.getCommanderId());
            System.out.println(response.getErrorMsg());
        }

        Alert alert = new Alert(response.getErrorCode().equals("0") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(response.getErrorMsg());
        alert.showAndWait();


    }

    public void refusDemande(Object object) {
        ArrayList<Command> commands = ApiService.getCommanderByObjectId(object.getId());
        Response response = null;
        for (Command cmd : commands) {
            System.out.println(cmd.getCommanderId());
            response = ApiService.refuseCommand(VarGlobal.currentUser.getId(), object.getId(), cmd.getCommanderId());
            System.out.println(response.getErrorMsg());
        }

        Alert alert = new Alert(response.getErrorCode().equals("0") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(response.getErrorMsg());
        alert.showAndWait();


    }



}
