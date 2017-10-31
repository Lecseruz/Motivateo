package com.example.magomed.motivateo.view.fragment;

import android.app.Activity;

public interface IWelcomeFragment {
    void login();
    boolean isSignUp();
    void signUp();
    void startActivity(Class<?> cls);
    Activity getMainActivity();
}
