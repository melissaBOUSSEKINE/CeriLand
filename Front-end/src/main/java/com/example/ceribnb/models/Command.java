package com.example.ceribnb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Command {

    @JsonProperty("id")
    private String id;
    @JsonProperty("objectId")
    private String objectId;
    @JsonProperty("commanderId")
    private String commanderId;
    @JsonProperty("ownerId")
    private String ownerId;

    public Command(){}

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

    public String getCommandId() {
        return commanderId;
    }

    public void setCommandId(String commandId) {
        this.commanderId = commandId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

}
