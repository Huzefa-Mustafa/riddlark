package com.group3.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {
    int errorCode;
    String message;
    User user;

    public Response() { }

    public Response(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() { return this.message; }
}
