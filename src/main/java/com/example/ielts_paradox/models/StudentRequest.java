package com.example.ielts_paradox.models;

public class StudentRequest {
    public String _id;
    public String courseTitle;
    public String studentMail;

    public StudentRequest(String _id, String courseTitle, String studentMail) {
        this._id = _id;
        this.courseTitle = courseTitle;
        this.studentMail = studentMail;
    }
}
