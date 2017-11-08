package com.example.magomed.motivateo.models;

public class Message<T> {
    private int code;
    private T body;

    public Message(int code, T body) {
        this.code = code;
        this.body = body;
    }

    public Message() {

    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
