package com.example.ielts_paradox.models;

public class BlogInfo {
    String _id;
    String date;
    String publishherName;
    String content;
    String thumbnail;

    public BlogInfo(String _id, String date, String publishherName, String content, String thumbnail) {
        this._id = _id;
        this.date = date;
        this.publishherName = publishherName;
        this.content = content;
        this.thumbnail = thumbnail;
    }
}
