package com.example.ceribnb.services;

import com.example.ceribnb.models.*;
import com.example.ceribnb.models.Object;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ApiService {

    private static String baseUrl = "http://127.0.0.1:5000";

    public static User login(String username, String password){
        User user = new User();
        try {
            URL url = new URL(baseUrl + "/login");
            URLConnection conn = url.openConnection();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            ObjectMapper objectMapper = new ObjectMapper();
//            user = objectMapper.readValue(reader, User.class);
//            System.out.println(user);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("username=" + username + "&password=" + password);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.readValue(reader, User.class);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return user;
    }

    public static void getAllObjects() {
        try {
            URL url = new URL(baseUrl + "/objects");
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
            URL url = new URL(baseUrl + "/user/commands_received/" + ownerId);
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
            URL url = new URL(baseUrl + "/user/panier/" + userId);
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
            URL url = new URL(baseUrl + "/object/comments/" + objectId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            comments = objectMapper.readValue(reader, new TypeReference<ArrayList<Comment>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return comments;
    }

    public static User getUserByUserId(int userId) {
        User user = new User();
        try {
            URL url = new URL(baseUrl + "/user/" + userId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.readValue(reader, User.class);
            System.out.println(user);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return user;
    }

    public static ArrayList<User> getUsersByUsername(String username) {
        ArrayList<User> users = new ArrayList<>();
        try {
            URL url = new URL(baseUrl + "/users/" + username);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            users = objectMapper.readValue(reader, new TypeReference<ArrayList<User>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return users;
    }

    public static ArrayList<Object> getObjectsByTitle(String title){
        ArrayList<Object> objects = new ArrayList<>();
        try {
            URL url = new URL(baseUrl + "/objects/" + title);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            objects = objectMapper.readValue(reader, new TypeReference<ArrayList<Object>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return objects;
    }
}
