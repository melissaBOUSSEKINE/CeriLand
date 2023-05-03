package com.example.ceribnb.models.vueModels;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class IconButton extends Button {

    public IconButton(String labelText, Node icon) {
        super(labelText, icon);
    }

    public void setIcon(Node icon) {
        setGraphic(icon);
    }

    public Node getIcon() {
        return getGraphic();
    }

}
