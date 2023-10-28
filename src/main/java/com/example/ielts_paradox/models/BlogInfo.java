package com.example.ielts_paradox.models;

public class BlogInfo {
    public String _id;
    public String title;
    public String date;
    public String publisherName;
    public String content;
    public String thumbnail;
    public String bandScore;
    public BlogInfo( String title,String _id, String date, String publisherName, String content, String thumbnail,String bandScore) {
        this._id = _id;
        this.title = title;
        this.date = date;
        this.publisherName = publisherName;
        this.content = content;
        this.thumbnail = thumbnail;
        this.bandScore = bandScore;
    }
}
