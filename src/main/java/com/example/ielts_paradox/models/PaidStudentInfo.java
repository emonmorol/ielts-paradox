package com.example.ielts_paradox.models;

public class PaidStudentInfo {
    public int _id;
    public String bkashNumber;
    public String email;
    public int courseId;
    public String enrollementDate;
    public boolean courseApproval;
    public boolean isExpired;
    public String transectionId;

    public PaidStudentInfo(int _id, String bkashNumber,String transectionId, String email, int courseId, String enrollementDate, boolean courseApproval, boolean isExpired) {
        this._id = _id;
        this.bkashNumber = bkashNumber;
        this.email = email;
        this.courseId = courseId;
        this.enrollementDate = enrollementDate;
        this.courseApproval = courseApproval;
        this.isExpired = isExpired;
    }
    public PaidStudentInfo(String bkashNumber,String transectionId, String email, int courseId, String enrollementDate, boolean courseApproval, boolean isExpired) {
        this.bkashNumber = bkashNumber;
        this.email = email;
        this.courseId = courseId;
        this.enrollementDate = enrollementDate;
        this.courseApproval = courseApproval;
        this.isExpired = isExpired;
    }
}
