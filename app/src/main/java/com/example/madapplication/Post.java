package com.example.madapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    String postId;
    Date dateCreated, dateUpdated;
    String bodyText;
    Boolean isEdited;
    String postedUserId;

    Post(String contents, String postedUserId)
    {
        this.bodyText= contents;
        this.postedUserId=postedUserId;
        //TODO- post id generation
        this.isEdited= false;
        this.dateCreated= new Date();
    }
}
