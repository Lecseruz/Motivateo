package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.view.fragment.IWelcomeFragment;

import java.io.IOException;

/**
 * Created by magomed on 29.10.17.
 */

public interface IWelcomeFragmentPresenter {
    void login(WelcomeFragmentPresenterImpl.OnUserGetListener userListener);
    void signUp(WelcomeFragmentPresenterImpl.OnUserGetListener userListener) throws IOException;
    void onCreate(IWelcomeFragment view);
    void saveUserProfile();
}
