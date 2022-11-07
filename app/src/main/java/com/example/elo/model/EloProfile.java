package com.example.elo.model;

public class EloProfile {

    int id, category;
    String name, description, previewDesc, previewName, progress;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public EloProfile(int id, String name, String description, String previewDesc, String previewName, int category, String progress) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.previewDesc = previewDesc;
        this.previewName = previewName;
        this.category = category;
        this.progress = progress;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getPreviewName() {
        return previewName;
    }

    public void setPreviewName(String previewName) {
        this.previewName = previewName;
    }

    public String getPreviewDesc() {
        return previewDesc;
    }

    public void setPreviewDesc(String previewDesc) {
        this.previewDesc = previewDesc;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
