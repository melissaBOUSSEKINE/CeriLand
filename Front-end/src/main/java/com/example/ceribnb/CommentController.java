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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommentController implements Initializable {


    @FXML
    static ListView<String> commentList = new ListView<String>();
    @FXML
    TextField commentText;


    Response response;
    public void addComment(){
        String newComment = commentText.getText();
        response = ApiService.addCommentToObject(VarGlobal.objetId  , VarGlobal.currentUser.getId() , newComment );
        commentText.clear();
            // Add the new comment to the ListView and refresh it
            commentList.getItems().add(newComment);
            commentList.refresh();

    }

    public void initialize(URL url, ResourceBundle rb) {


        //commentList.getItems().clear();
        /*commentList.setCellFactory(TextFieldListCell.forListView());

        ArrayList<Comment>  comments = ApiService.getCommentsByObjectId(VarGlobal.objetId);
        commentList.refresh();
        for (Comment comment : comments) {
            commentList.getItems().add(comment.getComment());
        }
        commentList.refresh();*/


        commentList.setCellFactory(TextFieldListCell.forListView());

        // Refresh the comment list
        refreshCommentList();

    }



    public static void refreshCommentList() {
        // Clear the comment list
        commentList.getItems().clear();

        // Load the comments for the current object ID
        List<Comment> comments = ApiService.getCommentsByObjectId(VarGlobal.objetId);

        // Add the comments to the ListView
        for (Comment comment : comments) {
            commentList.getItems().add(comment.getComment());
        }

        // Refresh the ListView
        commentList.refresh();
    }
}
