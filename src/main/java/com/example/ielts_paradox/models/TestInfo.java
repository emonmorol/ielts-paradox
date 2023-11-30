package com.example.ielts_paradox.models;

public class TestInfo {
    public String _id;
    public String meetLink;
    public String resultScore;
    public String resultLink;
    public String enrollmentDate;
    public String examDate;
    public String studentSubmissionLink;
    public String examModule;
    public String questionLink;
    public String practiceQuestionLink;
    public String studentMail;
    public String teacherMail;
    public String transectionId;
    public String bkashNumber;
    public boolean isAccepted;
    public boolean isTaken;

    public TestInfo(String _id,String meetLink, String resultScore, String resultLink, String enrollmentDate, String examDate, String studentSubmissionLink, String examModule, String questionLink, String practiceQuestionLink, String studentMail, String teacherMail, boolean isAccepted, boolean isTaken, String transectionId, String bkashNumber) {
        this._id = _id;
        this.meetLink = meetLink;
        this.resultScore = resultScore;
        this.resultLink = resultLink;
        this.enrollmentDate = enrollmentDate;
        this.examDate = examDate;
        this.studentSubmissionLink = studentSubmissionLink;
        this.examModule = examModule;
        this.questionLink = questionLink;
        this.practiceQuestionLink = practiceQuestionLink;
        this.studentMail = studentMail;
        this.teacherMail = teacherMail;
        this.isAccepted = isAccepted;
        this.isTaken = isTaken;
        this.transectionId = transectionId;
        this.bkashNumber = bkashNumber;
    }

    public TestInfo(String _id,String enrollmentDate, String examModule, String studentMail, String transectionId, String bkashNumber,boolean isAccepted,boolean isTaken) {
        this._id = _id;
        this.enrollmentDate = enrollmentDate;
        this.examModule = examModule;
        this.studentMail = studentMail;
        this.transectionId = transectionId;
        this.bkashNumber = bkashNumber;
        this.isAccepted = isAccepted;
        this.isTaken = isTaken;
    }


}
