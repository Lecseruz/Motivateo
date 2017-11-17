package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.view.fragment.ISignUpFragment;

public interface ISignUpFragmentPresenter {
    ListenerHandler<WelcomeFragmentPresenterImpl.OnUserGetListener> signUp(User user, WelcomeFragmentPresenterImpl.OnUserGetListener listener);
}
