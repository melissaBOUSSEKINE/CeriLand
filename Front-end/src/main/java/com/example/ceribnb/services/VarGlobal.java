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

    public static Button logoutBtn = new Button("Déconnexion");

    public static Text currentUserNameText = new Text("");

    public static Button profil = new Button("Profile");
    public static Button addButton = new Button("Ajouter au panier");

    public static Button deleteButton = new Button("Supprimer");

    public static Button ObjetProp = new Button("Objets proposés");
    public static Button demandObj = new Button("Demandes d'objets");

    public static Button valider = new Button("Valider");
    public static Button refuser = new Button("Refuser");



    public static String objetId;



}
