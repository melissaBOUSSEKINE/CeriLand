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
import java.util.Date;

public class ApiService {

    private static String baseUrl = "http://127.0.0.1:5000";

    /**
     * Gestion les users
     */
    public static User login(String username, String password){
        User user = new User();
        try {
            URL url = new URL(baseUrl + "/login");
            URLConnection conn = url.openConnection();
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

    public static User getUserByUserId(String userId) {
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

    /**
     * Gestion les objects
     */
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

    public static ArrayList<Object> getObjectsByDate(String date){
        ArrayList<Object> objects = new ArrayList<>();
        try {
            URL url = new URL(baseUrl + "/objects/date/" + date);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            objects = objectMapper.readValue(reader, new TypeReference<ArrayList<Object>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return objects;
    }

    //la liste d'objets !
    public static Object getObjectById(String objectId){
        Object object = new Object();
        try {
            URL url = new URL(baseUrl + "/object/" + objectId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            object = objectMapper.readValue(reader, Object.class);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return object;
    }

    public static ArrayList<Object> getAllObjectById(String ownerId){
        ArrayList<Object> objects = new ArrayList<>();
        try {
            URL url = new URL(baseUrl + "/objects/" + ownerId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            objects = objectMapper.readValue(reader, new TypeReference<ArrayList<Object>>(){});;
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return objects;
    }

    /**
     * Gestion les commandes
     */
    // les demandes!
    public static ArrayList<Command> getCommandsReceivedByUserId(String ownerId) {
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

    public static ArrayList<Command> getCommandsValided(String commanderId) {
        ArrayList<Command> commands = new ArrayList<>();
        try {
            URL url = new URL(baseUrl + "/commands/valided/" + commanderId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            commands = objectMapper.readValue(reader, new TypeReference<ArrayList<Command>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return commands;
    }

    public static ArrayList<Command> getCommandsSentByCommanderId(String commanderId) {
        ArrayList<Command> commands = new ArrayList<>();
        try {
            URL url = new URL(baseUrl + "/user/commands_sent/" + commanderId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            commands = objectMapper.readValue(reader, new TypeReference<ArrayList<Command>>(){});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return commands;
    }

    public static Response cancelCommand(String objectId, String commanderId){
        Response res = new Response();
        try {
            URL url = new URL(baseUrl + "/user/command_sent/cancel");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("objectId=" + objectId + "&commanderId=" + commanderId);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Response.class);
            System.out.println(res);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return res;
    }

    public static Response sendCommand(String objectId, String commanderId){
        Response res = new Response();
        try {
            URL url = new URL(baseUrl + "/user/send_command");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("objectId=" + objectId + "&commanderId=" + commanderId);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Response.class);
            System.out.println(res);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return res;
    }
    public static ArrayList<Command> getCommanderByObjectId(String objectId){
        ArrayList<Command> commands = new ArrayList<>();
        try {
            URL url = new URL(baseUrl + "/commands/object/" + objectId);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            commands = objectMapper.readValue(reader, new TypeReference<ArrayList<Command>>(){});;
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return commands;
    }

    public static Response valideCommand(String ownerId, String objectId, String commanderId){
        Response res = new Response();
        try {
            URL url = new URL(baseUrl + "/user/commands_received/valide");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("ownerId=" + ownerId + "&objectId=" + objectId + "&commanderId=" + commanderId);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Response.class);
            System.out.println(res);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return res;
    }

    public static Response refuseCommand(String ownerId, String objectId, String commanderId){
        Response res = new Response();
        try {
            URL url = new URL(baseUrl + "/user/commands_received/refuse");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("ownerId=" + ownerId + "&objectId=" + objectId + "&commanderId=" + commanderId);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Response.class);
            System.out.println(res);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return res;
    }

    /**
     * Gestion les panier
     */
    public static ArrayList<Panier> getPanierByUserId(String userId) {
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

    public static Response addObjectIntoPanier(String objectId, String userId){
        Response res = new Response();
        try {
            URL url = new URL(baseUrl + "/user/panier/add_object");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("objectid=" + objectId + "&userid=" + userId);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Response.class);
            System.out.println(res);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return res;
    }

    public static Response removeObjectFromPanier(String objectId, String userId){
        Response res = new Response();
        try {
            URL url = new URL(baseUrl + "/user/panier/remove_object");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("objectid=" + objectId + "&userid=" + userId);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Response.class);
            System.out.println(res);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return res;
    }

    public static Response removeAllObjectsFromPanier(String userId) {
        Response res = new Response();
        try {
            System.out.println(userId);
            URL url = new URL(baseUrl + "/user/panier/remove_all_objects");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("userid=" + userId);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Response.class);
            System.out.println(res);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return res;
    }

    /**
     * Gestion les commentaires
     */
    public static ArrayList<Comment> getCommentsByObjectId(String objectId) {
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

    public static Response addCommentToObject(String objectId, String userId, String comment){
        Response res = new Response();
        try {
            URL url = new URL(baseUrl + "/object/comments/add_comment");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("objectid=" + objectId + "&userid=" + userId + "&comment=" + comment);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Response.class);
            System.out.println(res);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return res;
    }

    public static Response deleteCommentToObject(String commentId){
        Response res = new Response();
        try {
            URL url = new URL(baseUrl + "/object/comments/delete_comment");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("commentid=" + commentId);
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Response.class);
            System.out.println(res);
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return res;
    }

}
