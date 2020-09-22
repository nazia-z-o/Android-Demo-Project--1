package com.example.jasonexample;

public class Comments {
    private final  String post;
    private final String id;
    private final  String name;
    private final String email;
    private String body;
    public Comments(String post, String id, String name, String email, String body) {
        this.post = post;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public String getPost() {
        return post;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String newPost) {
        body=newPost;
    }
}
