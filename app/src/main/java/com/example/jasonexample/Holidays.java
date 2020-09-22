package com.example.jasonexample;

public class Holidays {

    private final String title;
    private String body;
   private final  String user;
    private final String id;

    public Holidays(String user,String id,String title, String body) {
       this.user = user;
        this.id = id;
        this.body=body;
        this.title=title;

    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

   public String getUser() {
       return user;
  }

    public String getId() {
        return id;
    }

    public void setBody(String newPost) {
        body = newPost;
    }
}
