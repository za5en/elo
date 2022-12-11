package com.example.elo.model;

public class Request {
    String userName;
    String userType;
    int id;

    public Request(String userName, String userType, int id)  {
        this.userName = userName;
        this.userType = userType;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
