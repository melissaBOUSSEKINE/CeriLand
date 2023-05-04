package com.example.ceribnb.services;

import com.example.ceribnb.models.Object;
import com.example.ceribnb.models.User;
import javafx.scene.control.Button;
import javafx.scene.text.*;

import java.util.ArrayList;

public class VarGlobal {

    public static ArrayList<Object> allObjects;

//    public static ArrayList<User> allUser;

    public static User currentUser;

    public static Button loginBtn = new Button("Connexion");

    public static Button logoutBtn = new Button("Déonnexion");

    public static Text currentUserNameText = new Text("");

}
