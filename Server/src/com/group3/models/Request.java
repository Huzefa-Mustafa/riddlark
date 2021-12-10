package com.group3.models;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private int selectedOption;   // MENU CODE TYPE
    private String userReply;
    User user;

    public Request(){}

    public Request(int selectedOption, User user) {
        this.selectedOption = selectedOption;
        this.user = user;
    }
    public Request(int selectedOption,String userReply, User user) {
        this.selectedOption = selectedOption;
        this.userReply = userReply;
        this.user = user;
    }

    public int getSelectedOption() { return this.selectedOption; }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserReply() { return this.userReply; }

    public void setUserReply(String userReply) { this.userReply = userReply; }

}
