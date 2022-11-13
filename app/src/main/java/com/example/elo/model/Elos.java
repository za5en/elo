package com.example.elo.model;

import java.util.List;

public class Elos {

    int id, category;
    String name, description, previewDesc, previewName;
    List<tagCategory> cats;
    boolean isPrivate;

    public Elos(int id, String name, String description, String previewDesc, String previewName, int category, List<tagCategory> cats, boolean isPrivate) {
        this.id = id;
        this.name = name;
        this.description = description;
        //this.color = color;
        this.previewDesc = previewDesc;
        //this.elosPreBg = elosPreBg;
        this.previewName = previewName;
        this.category = category;
        this.cats = cats;
        this.isPrivate = isPrivate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
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

    public List<tagCategory> getCats() {
        return cats;
    }

    public void setCats(List<tagCategory> cats) {
        this.cats = cats;
    }
}
