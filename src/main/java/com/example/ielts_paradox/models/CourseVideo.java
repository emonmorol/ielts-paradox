package com.example.ielts_paradox.models;

import java.util.ArrayList;

public class CourseVideo {
    public int _id;
    public String videoTitle;
    public String videoURI;
    public boolean isWatched;

    public CourseVideo(int _id, String videoTitle, String videoURI, boolean isWatched) {
        this._id = _id;
        this.videoTitle = videoTitle;
        this.videoURI = videoURI;
        this.isWatched = isWatched;
    }

    @Override
    public String toString() {
        return "CourseVideo{" +
                "_id=" + _id +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoURI='" + videoURI + '\'' +
                ", isWatched=" + isWatched +
                '}';
    }
}
