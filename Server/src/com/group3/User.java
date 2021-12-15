package com.group3;

public class User  {
    String name;
    String password;
    User user;
    boolean isReady;
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

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
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
