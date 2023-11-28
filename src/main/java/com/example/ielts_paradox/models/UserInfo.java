package com.example.ielts_paradox.models;

import java.sql.Struct;

public class UserInfo {
    public String fullName;
    public String email;
    public String contactNumber;
    public String password;
    public String bio;
    public boolean isTeacher;
    public UserInfo(){};
    public UserInfo(String fullName, String email, String contactNumber, String password, boolean isTeacher,String bio) {
        this.fullName = fullName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.isTeacher = isTeacher;
        this.bio = bio;
    }

    public UserInfo(String fullName, String email, String contactNumber, boolean isTeacher, String bio) {
        this.fullName = fullName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.isTeacher = isTeacher;
        this.bio = bio;
    }
}
