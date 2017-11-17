package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.models.User;

/**
 * Created by magomed on 17.11.17.
 */

public interface IAuthorizationFragmentPresenter {
    ListenerHandler<WelcomeFragmentPresenterImpl.OnUserGetListener> signIn(User use, WelcomeFragmentPresenterImpl.OnUserGetListener listener);
    ListenerHandler<WelcomeFragmentPresenterImpl.OnUserGetListener> signUp(User user, WelcomeFragmentPresenterImpl.OnUserGetListener listener);
}
