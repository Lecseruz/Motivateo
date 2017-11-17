package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.view.fragment.ISignInFragment;

public interface ISignInFragmentPresenter {
    ListenerHandler<WelcomeFragmentPresenterImpl.OnUserGetListener> signIn(User use, WelcomeFragmentPresenterImpl.OnUserGetListener listener);
}
