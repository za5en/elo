package com.example.elo.model;

public class AcceptTask {
    public static String userName;
    public static String userTask;

    public AcceptTask(String userName, String userTask)  {
        this.userName = userName;
        this.userTask = userTask;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTask() {
        return userTask;
    }

    public void setUserTask(String userTask) {
        this.userTask = userTask;
    }
}
