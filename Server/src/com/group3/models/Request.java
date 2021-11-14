package com.group3.models;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    int type;   // MENU CODE TYPE
    User user;

    public Request(){}

    public Request(int type, User user) {
        this.type = type;
        this.user = user;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
