package com.example.elo.model;

import java.util.List;

public class Users {

    int id;
    String username, userLevel, email, password;
    List<tagCategory> cats;
    //elo_part
    //elo_created
    //photo
    //tags

    public Users(int id, String username, String userLevel, String email, String password, List<tagCategory> cats) {
        this.id = id;
        this.username = username;
        this.userLevel = userLevel;
        this.email = email;
        this.password = password;
        this.cats = cats;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<tagCategory> getCats() {
        return cats;
    }

    public void setCats(List<tagCategory> cats) {
        this.cats = cats;
    }
}