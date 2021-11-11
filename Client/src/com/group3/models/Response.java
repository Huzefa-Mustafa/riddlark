package com.group3.models;


import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {
    int errorCode;
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

}
