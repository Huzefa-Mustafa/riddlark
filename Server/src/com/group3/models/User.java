package com.group3.models;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String password;
    boolean isReady;

    public User() {
    }

    public User( String name, String password) {
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

    public void display(){
        System.out.println("\t\tname :" + name);
        System.out.println("\t\tpass :" + password);
    }

}
