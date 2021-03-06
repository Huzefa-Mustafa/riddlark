package com.group3.models;

import java.io.Serializable;

public class User implements Serializable {
    String email;
    String name;
    String password;

    public User() {
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        System.out.println("\t\temail :" + email);
        System.out.println("\t\tname :" + name);
        System.out.println("\t\tpass :" + password);
    }

}
