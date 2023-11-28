package com.example.ielts_paradox.models;

public class StoryInfo {
    public String _id;
    public String mainStory;
    public String studentImage;
    public String studentName;
    public String bandScore;

    public StoryInfo(String _id, String mainStory, String studentImage, String studentName, String bandScore) {
        this._id = _id;
        this.mainStory = mainStory;
        this.studentImage = studentImage;
        this.studentName = studentName;
        this.bandScore = bandScore;
    }
    public StoryInfo(String mainStory, String studentImage, String studentName, String bandScore) {
        this.mainStory = mainStory;
        this.studentImage = studentImage;
        this.studentName = studentName;
        this.bandScore = bandScore;
    }
}
