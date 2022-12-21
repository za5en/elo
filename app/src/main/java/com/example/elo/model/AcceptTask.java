package com.example.elo.model;

public class AcceptTask {
    String userName;
    String userTask;
    int id;
    int eloId;
    int userId;

    public AcceptTask(String userName, String userTask, int id, int eloId, int userId)  {
        this.userName = userName;
        this.userTask = userTask;
        this.id = id;
        this.eloId = eloId;
        this.userId = userId;
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

    public String getUserTask() {
        return userTask;
    }

    public void setUserTask(String userTask) {
        this.userTask = userTask;
    }

    public int getEloId() {
        return eloId;
    }

    public void setEloId(int eloId) {
        this.eloId = eloId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
