package com.example.ielts_paradox.singletons;

import com.example.ielts_paradox.models.UserInfo;

public class UserSingleTon {
    private static UserSingleTon instance;
    private UserInfo user;
    private UserSingleTon(UserInfo user){
        this.user = user;
    }
    public static UserSingleTon getInstance(UserInfo user){
        if(instance == null){
            instance = new UserSingleTon(user);
        }
        return instance;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
