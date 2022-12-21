package com.example.elo.model;

import java.util.List;

public class Users {

    int id;
    String username, userLevel, userAccessLevel, email, password, tag1, tag2, tag3;
    //elo_part
    //elo_created
    //photo
    //tags

    public Users(int id, String username, String userLevel, String userAccessLevel, String email, String password, String tag1, String tag2, String tag3) {
        this.id = id;
        this.username = username;
        this.userLevel = userLevel;
        this.userAccessLevel = userAccessLevel;
        this.email = email;
        this.password = password;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
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

    public String getUserAccessLevel() {
        return userAccessLevel;
    }

    public void setUserAccessLevel(String userAccessLevel) {
        this.userAccessLevel = userAccessLevel;
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

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }
}