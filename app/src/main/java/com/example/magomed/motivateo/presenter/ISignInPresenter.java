package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.view.fragment.ISignInFragment;

/**
 * Created by magomed on 31.10.17.
 */

public interface ISignInPresenter {
    void signIn();
    void onCreate(ISignInFragment view);
}
