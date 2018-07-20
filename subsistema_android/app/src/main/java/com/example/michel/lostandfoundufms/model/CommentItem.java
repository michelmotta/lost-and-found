package com.example.michel.lostandfoundufms.model;

public class CommentItem {

    private int id;
    private int objectId;
    private int userId;
    private String comment;
    private String created;
    private String modified;
    private UserItem userItem;
    private ObjectItem objectItem;

    public CommentItem(int id, int objectId, int userId, String comment, String created, String modified, UserItem userItem, ObjectItem objectItem) {
        this.id = id;
        this.objectId = objectId;
        this.userId = userId;
        this.comment = comment;
        this.created = created;
        this.modified = modified;
        this.userItem = userItem;
        this.objectItem = objectItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public UserItem getUserItem() {
        return userItem;
    }

    public void setUserItem(UserItem userItem) {
        this.userItem = userItem;
    }

    public ObjectItem getObjectItem() {
        return objectItem;
    }

    public void setObjectItem(ObjectItem objectItem) {
        this.objectItem = objectItem;
    }
}
