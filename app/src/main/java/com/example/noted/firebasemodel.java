package com.example.noted;

public class firebasemodel {

     private  String title;
     private  String content;

    public firebasemodel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public firebasemodel(){

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
