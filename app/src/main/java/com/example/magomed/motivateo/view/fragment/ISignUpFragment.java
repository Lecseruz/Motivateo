package com.example.magomed.motivateo.view.fragment;

import com.example.magomed.motivateo.models.User;

/**
 * Created by magomed on 31.10.17.
 */

public interface ISignUpFragment {
    boolean isEmpty();
    User getUserInformation();
    void startActivity(Class<?> cls);
    void errorSignIn();
}
