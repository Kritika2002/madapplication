package com.example.madapplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Post {
    String postId;
    Date dateCreated, dateUpdated;
    String title, body;
    Boolean isEdited;
    String postedUserId;

    Post(String title, String contents, String postedUserId)
    {
        this.title = title;
        this.body= contents;
        this.postedUserId=postedUserId;
        //TODO- post id generation
        this.isEdited= false;
        this.dateCreated= new Date();
    }

    public static ArrayList<Post> getDbPosts() {
        ArrayList<Post> posts = new ArrayList<Post>();
        // TODO populate posts from db calls
        return posts;
    }
}
