package com.example.michel.lostandfoundufms.model;

public class ObjectItem {

    private int id;
    private int user_id;
    private String title;
    private String solved;
    private String type;
    private String date;
    private String image;
    private String description;
    private String created;
    private String modified;

    public ObjectItem(int id, int user_id, String title, String solved, String type, String date, String image, String description, String created, String modified) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.solved = solved;
        this.type = type;
        this.date = date;
        this.image = image;
        this.description = description;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSolved() {
        return solved;
    }

    public void setSolved(String solved) {
        this.solved = solved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
