package com.example.magomed.motivateo.models;


import com.example.magomed.motivateo.R;

public enum TaskObject {
    ALL(R.string.All, R.layout.fragment_all),
    TODAY(R.string.Today, R.layout.fragment_today);

    private int type;
    private int fragment;

    TaskObject(int type, int fragment) {
        this.type = type;
        this.fragment = fragment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFragment() {
        return fragment;
    }

    public void setFragment(int fragment) {
        this.fragment = fragment;
    }
}
