package com.example.ceribnb;

import com.example.ceribnb.models.Command;
import com.example.ceribnb.models.Comment;
import com.example.ceribnb.models.Object;
import com.example.ceribnb.models.Response;
import com.example.ceribnb.services.ApiService;
import com.example.ceribnb.services.VarGlobal;
import javafx.css.converter.StringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommentController implements Initializable {


    @FXML
    ListView<String> commentList;
    @FXML
    TextField commentText;




    Response response;
    public void addComment(){
        //commentList.getItems().clear();
        String newComment = commentText.getText();
        if(newComment == null || newComment.isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Rentrez un commentaire valide");
            alert.showAndWait();

        }else if(VarGlobal.currentUser == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Pour ajouter un commentaire veuillez se connecter");
            alert.showAndWait();

        }else{
            response = ApiService.addCommentToObject(VarGlobal.objetId  , VarGlobal.currentUser.getId() , newComment );
            commentText.clear();
            // Add the new comment to the ListView and refresh it
            String commentWithUsername = VarGlobal.currentUser.getUsername() + " a commenté : "+ newComment;
            commentList.getItems().add(commentWithUsername);
            //commentList.getItems().add(newComment);
            commentList.refresh();
        }


    }

    public void initialize(URL url, ResourceBundle rb) {



        commentList.setCellFactory(TextFieldListCell.forListView());

        for (String comment : commentList.getItems()) {
            System.out.println("111111 " + comment);
        }

        // Refresh the comment list
        refreshCommentList();
        for (String comment : commentList.getItems()) {
            System.out.println("2222222 " + comment);
        }


    }



    public void refreshCommentList() {
        // Clear the comment list
        ///commentList.getItems().clear();

        System.out.println("helppp " + VarGlobal.objetId);
        // Load the comments for the current object ID
        List<Comment> comments = ApiService.getCommentsByObjectId(VarGlobal.objetId);

        // Add the comments to the ListView
        for (Comment comment : comments) {
            System.out.println("comment est "+comment);
            if(VarGlobal.currentUser != null){
                String commentWithUsername = VarGlobal.currentUser.getUsername() + " a commenté : "+ comment.getComment();
                commentList.getItems().add(commentWithUsername);
            }else{
                String commentWithUsername = comment.getComment();
                commentList.getItems().add(commentWithUsername);

            }

        }

        for (String comment : commentList.getItems()) {
            System.out.println("yessss " + comment);
        }


        // Refresh the ListView
        commentList.refresh();

        for (String comment : commentList.getItems()) {
            System.out.println("noooooo " + comment);
        }
    }
}
