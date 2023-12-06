package com.example.ielts_paradox.models;

import java.util.ArrayList;

public class PaidStudentInfo {
    public int _id;
    public String bkashNumber;
    public String email;
    public int courseId;
    public String enrollementDate;
    public boolean courseApproval;
    public boolean isExpired;
    public String transectionId;
    public ArrayList<CourseVideo> content;

    public PaidStudentInfo(int _id, String bkashNumber,String transectionId, String email, int courseId, String enrollementDate, boolean courseApproval, boolean isExpired,ArrayList<CourseVideo> content) {
        this._id = _id;
        this.bkashNumber = bkashNumber;
        this.transectionId = transectionId;
        this.email = email;
        this.courseId = courseId;
        this.enrollementDate = enrollementDate;
        this.courseApproval = courseApproval;
        this.isExpired = isExpired;
        this.content = content;
    }
    public PaidStudentInfo(String bkashNumber,String transectionId, String email, int courseId, String enrollementDate, boolean courseApproval, boolean isExpired,ArrayList<CourseVideo> content) {
        this.bkashNumber = bkashNumber;
        this.email = email;
        this.transectionId = transectionId;
        this.courseId = courseId;
        this.enrollementDate = enrollementDate;
        this.courseApproval = courseApproval;
        this.isExpired = isExpired;
        this.content = content;
    }
    public PaidStudentInfo(int _id, String bkashNumber,String transectionId, String email, int courseId, String enrollementDate, boolean courseApproval, boolean isExpired) {
        this._id = _id;
        this.bkashNumber = bkashNumber;
        this.transectionId = transectionId;
        this.email = email;
        this.courseId = courseId;
        this.enrollementDate = enrollementDate;
        this.courseApproval = courseApproval;
        this.isExpired = isExpired;
    }
}
