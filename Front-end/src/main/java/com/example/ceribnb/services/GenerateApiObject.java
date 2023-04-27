package com.example.ceribnb.services;

import com.example.ceribnb.models.*;
import com.example.ceribnb.models.Object;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GenerateApiObject {

    public static void getAllObjects() {
        try {
            URL url = new URL("http://127.0.0.1:5000/objects");
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            VarGlobal.allObjects = objectMapper.readValue(reader, new TypeReference<ArrayList<Object>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static ArrayList<Command> getCommandsReceivedByUserId(int ownerId) {
        ArrayList<Command> commands = new ArrayList<>();
        try {
            URL url = new URL("http://127.0.0.1:5000/user/commands_received/" + ownerId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            commands = objectMapper.readValue(reader, new TypeReference<ArrayList<Command>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return commands;
    }

    public static ArrayList<Panier> getPanierByUserId(int userId) {
        ArrayList<Panier> paniers = new ArrayList<>();
        try {
            URL url = new URL("http://127.0.0.1:5000/user/panier/" + userId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            paniers = objectMapper.readValue(reader, new TypeReference<ArrayList<Panier>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return paniers;
    }

    public static ArrayList<Comment> getCommentsByObjectId(int objectId) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            URL url = new URL("http://127.0.0.1:5000/object/comments/" + objectId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            comments = objectMapper.readValue(reader, new TypeReference<ArrayList<Comment>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return comments;
    }

//    public static User getUserByUserId(int userId) {
//
//    }
}
