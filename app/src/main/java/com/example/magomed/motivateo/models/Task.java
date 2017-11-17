package com.example.magomed.motivateo.models;

public class Task {
    private String name;
    private String time;
    private String repeat;
    private boolean remind;
    private String type;
    private String count;

    public Task(String name, String time, String repeat, boolean remind, String type, String count) {
        this.name = name;
        this.time = time;
        this.repeat = repeat;
        this.remind = remind;
        this.type = type;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public boolean isRemind() {
        return remind;
    }

    public void setRemind(boolean remind) {
        this.remind = remind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
