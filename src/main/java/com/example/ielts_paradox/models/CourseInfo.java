package com.example.ielts_paradox.models;

import java.util.ArrayList;

public class CourseInfo {
    public String _id;
    public String title;
    public String[] features;
    public String thumbnail;
    public int price;
    public boolean isReleased;
    public int discount;
    public String[] curriculum;
    public ArrayList<Faq> faqs;
    public String details;
    public String[] sidebarPoint;
    public String instructorName;
    public String routine;
    public int messagePort;
    public ArrayList<CourseVideo> content;

    public CourseInfo(){};
    public CourseInfo(String _id, String title, String thumbnail, int price, boolean isReleased, int discount, String details, String instructorName,int messagePort) {
        this._id = _id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.price = price;
        this.isReleased = isReleased;
        this.discount = discount;
        this.details = details;
        this.instructorName = instructorName;
        this.routine = routine;
        this.messagePort = messagePort;
    }

    public CourseInfo(String _id, String title, String[] features, String thumbnail, int price, boolean isReleased, int discount, String[] curriculum, ArrayList<Faq> faqs, String details, String[] sidebarPoint, String instructorName,String routine,int messagePort,ArrayList<CourseVideo> content) {
        this._id = _id;
        this.title = title;
        this.features = features;
        this.thumbnail = thumbnail;
        this.price = price;
        this.isReleased = isReleased;
        this.discount = discount;
        this.curriculum = curriculum;
        this.faqs = faqs;
        this.details = details;
        this.sidebarPoint = sidebarPoint;
        this.instructorName = instructorName;
        this.routine = routine;
        this.messagePort = messagePort;
        this.content = content;
    }
}
