package com.example.ceribnb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Object {

    @JsonProperty("id")
    private String id;
    @JsonProperty("ownerId")
    private String ownerId;
    @JsonProperty("img_url")
    private String imgUrl;
    @JsonProperty("title")
    private String title;
    @JsonProperty("date_dispo_start")
    private String dateDispoStart;

    @JsonProperty("date_dispo_end")
    private String dateDispoEnd;
    @JsonProperty("prix")
    private String prix;
    @JsonProperty("res_status")
    private String resStatus;
    @JsonProperty("res_by")
    private String resBy;

    public Object(){}

    public String getResStatus() {
        return resStatus;
    }

    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

    public String getResBy() {
        return resBy;
    }

    public void setResBy(String resBy) {
        this.resBy = resBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateDispoStart() {
        return dateDispoStart;
    }

    public void setDateDispoStart(String dateDispoStart) {
        this.dateDispoStart = dateDispoStart;
    }

    public String getDateDispoEnd() {
        return dateDispoEnd;
    }

    public void setDateDispoEnd(String dateDispoEnd) {
        this.dateDispoEnd = dateDispoEnd;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
