package com.example.ielts_paradox.models;

import java.util.ArrayList;

public class CourseInfo {
    String _id;
    String title;
    String[] features;
    String thumbnail;
    int price;
    boolean isReleased;
    int discount;
    String[] curriculum;
    ArrayList<Faq> faqs;
    String details;
    String[] sidebarPoint;

    public CourseInfo(String _id, String title, String[] features, String thumbnail, int price, boolean isReleased, int discount, String[] curriculum, ArrayList<Faq> faqs, String details, String[] sidebarPoint) {
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
    }


}
