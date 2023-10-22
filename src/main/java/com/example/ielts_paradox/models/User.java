package com.example.ielts_paradox.models;

public class User {
    public String fullName;
    public String email;
    public String contactNumber;
    public String password;
    public boolean isTeacher;

    public User(String fullName, String email, String contactNumber, String password, boolean isTeacher) {
        this.fullName = fullName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.isTeacher = isTeacher;
    }

}
