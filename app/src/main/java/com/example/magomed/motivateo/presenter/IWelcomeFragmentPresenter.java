package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.view.fragment.WelcomeFragment;

/**
 * Created by magomed on 29.10.17.
 */

public interface IWelcomeFragmentPresenter {
    void login();
    void signUp();
    void onCreate(WelcomeFragment view);
    void onPause();
    void saveUserProfile();
    boolean isSignUp();
}
