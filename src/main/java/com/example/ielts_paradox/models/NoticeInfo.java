package com.example.ielts_paradox.models;

public class NoticeInfo {
    public int _id;
    public String text;
    public String title;
    public String recieverMail;
    public String senderMail;
    public String senderName;
    public String module;

    public NoticeInfo(int _id, String text, String title, String recieverMail, String senderMail, String senderName, String module) {
        this._id = _id;
        this.text = text;
        this.title = title;
        this.recieverMail = recieverMail;
        this.senderMail = senderMail;
        this.senderName = senderName;
        this.module = module;
    }

    public NoticeInfo(String text, String title, String recieverMail, String senderMail, String senderName, String module) {
        this.text = text;
        this.title = title;
        this.recieverMail = recieverMail;
        this.senderMail = senderMail;
        this.senderName = senderName;
        this.module = module;
    }

    public int get_id() {
        return _id;
    }

    @Override
    public String toString() {
        return "NoticeInfo{" +
                "_id=" + _id +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", recieverMail='" + recieverMail + '\'' +
                ", senderMail='" + senderMail + '\'' +
                ", senderName='" + senderName + '\'' +
                ", module='" + module + '\'' +
                '}';
    }
}
