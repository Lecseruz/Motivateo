package com.example.magomed.motivateo.view.fragment;

/**
 * Created by magomed on 18.11.17.
 */

public interface IAuthorizationFragment {
    void popFragmentFromStack();
    boolean isEmpty();
    void startActivity(Class<?> cls);
    void errorSignIn();
}
