package com.example.magomed.motivateo.view.fragment;

public interface ISignInFragment {
    boolean isEmpty();
    void startActivity(Class<?> cls);
    void errorSignIn();
}
