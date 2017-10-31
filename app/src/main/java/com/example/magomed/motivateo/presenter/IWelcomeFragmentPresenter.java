package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.view.fragment.IWelcomeFragment;

/**
 * Created by magomed on 29.10.17.
 */

public interface IWelcomeFragmentPresenter {
    void login();
    void signUp();
    void onCreate(IWelcomeFragment view);
    void onPause();
    void saveUserProfile();
    boolean isSignUp();
}
