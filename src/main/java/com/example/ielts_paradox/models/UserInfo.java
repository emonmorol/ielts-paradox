package com.example.ielts_paradox.models;

public class UserInfo {
    public String fullName;
    public String email;
    public String contactNumber;
    public String password;
    public boolean isTeacher;

    public UserInfo(String fullName, String email, String contactNumber, String password, boolean isTeacher) {
        this.fullName = fullName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.isTeacher = isTeacher;
    }

}
