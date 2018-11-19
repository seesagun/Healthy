package com.example.lab203_26.healthy.Comment;

public class Comment {

    private int postId;
    private int id;
    private String body;
    private String name;
    private String email;

    public Comment(int postId, int id, String body, String name, String email) {
        this.postId = postId;
        this.id = id;
        this.body = body;
        this.name = name;
        this.email = email;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
