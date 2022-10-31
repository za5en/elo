package com.example.elo.model;

public class EloProfile {
    int id;
    String name, previewDesc, previewName;

    public EloProfile(int id, String name, String previewDesc, String previewName) {
        this.id = id;
        this.name = name;
        this.previewDesc = previewDesc;
        this.previewName = previewName;
    }

    public String getPreviewDesc() {
        return previewDesc;
    }

    public void setPreviewDesc(String previewDesc) {
        this.previewDesc = previewDesc;
    }

    public String getPreviewName() {
        return previewName;
    }

    public void setPreviewName(String previewName) {
        this.previewName = previewName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
