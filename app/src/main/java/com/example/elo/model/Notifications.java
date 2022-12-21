package com.example.elo.model;

public class Notifications {

    int id, userId;
    String theme, text, fullText, date, eloDesc;
    boolean read;

    public Notifications(int id, String theme, String text, String fullText, String date, String eloDesc, boolean read, int userId) {
        this.id = id;
        this.theme = theme;
        this.text = text;
        this.fullText = fullText;
        this.date = date;
        this.eloDesc = eloDesc;
        this.read = read;
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEloDesc() {
        return eloDesc;
    }

    public void setEloDesc(String eloDesc) {
        this.eloDesc = eloDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}