package com.example.ceribnb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("res_message")
    private String errorMsg;

    public Response(){}

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
