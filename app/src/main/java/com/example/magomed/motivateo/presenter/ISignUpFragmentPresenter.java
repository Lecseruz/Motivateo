package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.view.fragment.ISignInFragment;
import com.example.magomed.motivateo.view.fragment.ISignUpFragment;

/**
 * Created by magomed on 31.10.17.
 */

public interface ISignUpFragmentPresenter {
    void signUp();
    void onCreate(ISignUpFragment view);

}
