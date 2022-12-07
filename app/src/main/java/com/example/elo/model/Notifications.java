package com.example.elo.model;

public class Notifications {

    int id;
    String theme, text, fullText;
    boolean read;

    public Notifications(int id, String theme, String text, String fullText, boolean read) {
        this.id = id;
        this.theme = theme;
        this.text = text;
        this.fullText = fullText;
        this.read = read;
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
}