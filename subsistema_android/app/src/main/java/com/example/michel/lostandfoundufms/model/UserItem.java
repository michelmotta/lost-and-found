package com.example.michel.lostandfoundufms.model;

public class UserItem {

    private int id;
    private String username;
    private String email;
    private String created;
    private String modified;

    public UserItem(int id, String username, String email, String created, String modified) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
