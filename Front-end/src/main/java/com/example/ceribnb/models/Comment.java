package com.example.ceribnb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    @JsonProperty("id")
    private String id;
    @JsonProperty("objectId")
    private String objectId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("comment")
    private String comment;

    public Comment(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
