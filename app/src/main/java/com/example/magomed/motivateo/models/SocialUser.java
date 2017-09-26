package com.example.magomed.motivateo.models;



public class SocialUser {

    private String userID;

    private String token;

    public SocialUser(){
        ;
    }

    public SocialUser(String userID, String token) {
        this.userID = userID;
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
