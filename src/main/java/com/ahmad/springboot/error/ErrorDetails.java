package com.ahmad.springboot.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorDetails {

    private String message;
    private String uri;

    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timeStamp;

    public ErrorDetails(String message, String uri) {
        //Call the default constructor to init the value
        this();
        this.message = message;
        this.uri = uri;
    }

    public ErrorDetails() {
        this.timeStamp = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
