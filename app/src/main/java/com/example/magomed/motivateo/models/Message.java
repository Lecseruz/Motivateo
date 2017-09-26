package com.example.magomed.motivateo.models;

public class Message<T> {
    private int code;
    private T user;

    public Message(int code, T user) {
        this.code = code;
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }
}
