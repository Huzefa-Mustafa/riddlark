package com.group3;

public class User  {
    String name;
    String password;
    User user;
    boolean isReady;
    String userReply;
    String groupID;

    public User() {

    }
    public User(String groupID, User user) {
        this.groupID = groupID;
        this.user = user;
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.isReady = false;
    }

    public boolean getIsReadyState() {
        return isReady;
    }

    public void setIsReadyState(boolean ready) {
        isReady = ready;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupID() {
        return this.groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getUserReply() {
        return this.userReply;
    }

    public void setUserReply(String userReply) {
        this.userReply = userReply;
    }
    public void display(){
        System.out.println("\t\tname :" + name);
        System.out.println("\t\tpass :" + password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", isReady=" + isReady +
                ", groupID='" + groupID + '\'' +
                '}';
    }
}
