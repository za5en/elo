package com.example.elo.model;

import java.util.List;

public class Notifications {

    int id;
    String theme, text, fullText;

    public Notifications(int id, String theme, String text, String fullText) {
        this.id = id;
        this.theme = theme;
        this.text = fullText;
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