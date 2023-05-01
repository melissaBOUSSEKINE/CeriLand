package com.example.ceribnb.models.vueModels;

import com.example.ceribnb.models.Object;
import com.example.ceribnb.services.VarGlobal;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ObejctCard {

    private HBox hBox;

    public ObejctCard(Object object, Image image){

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(230);
        imageView.setFitHeight(180);
        imageView.setEffect(dropShadow);

        long timeStampStart = (long) Double.parseDouble(object.getDateDispoStart()) * 1000;
        Date dateStart = new Date(timeStampStart);
        SimpleDateFormat sdfStart = new SimpleDateFormat("yyyy/MM/dd");
        String dateStartString = sdfStart.format(dateStart);

        long timeStampEnd = (long) Double.parseDouble(object.getDateDispoEnd()) * 1000;
        Date dateEnd = new Date(timeStampEnd);
        SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy/MM/dd");
        String dateEndString = sdfEnd.format(dateEnd);

        Text title = new Text(object.getTitle());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        Text prix = new Text(object.getPrix());
        prix.setFill(Color.RED);

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

        TextFlow textFlowTitle = new TextFlow();
        textFlowTitle.setLayoutX(1);
        textFlowTitle.setLayoutY(1);

        textFlowTitle.setPrefWidth(250);
        textFlowTitle.getChildren().addAll(title);

        VBox vBoxInfo = new VBox(10);
        VBox.setMargin(textFlowTitle, new Insets(10));
        vBoxInfo.getChildren().add(textFlowTitle);
        VBox.setMargin(labelDate, new Insets(10));
        vBoxInfo.getChildren().add(labelDate);
        VBox.setMargin(stackPaneDateDispo, new Insets(5));
        vBoxInfo.getChildren().add(stackPaneDateDispo);
//        VBox.setMargin(stackPaneDateDispo, new Insets(5));
//        vBoxInfo.getChildren().add(stackPaneDateDispo);

        this.hBox = new HBox(imageView, vBoxInfo);
        this.hBox.setAlignment(Pos.CENTER_LEFT);

        BorderStroke borderStroke = new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, null, new BorderWidths(1));
        Border border = new Border(borderStroke);

        this.hBox.getStyleClass().add("hbox-with-shadow");
        this.hBox.setBorder(border);
        this.hBox.setPadding(new Insets(10));
        this.hBox.setSpacing(10);

    }

    public HBox gethBox() {
        return hBox;
    }
}
