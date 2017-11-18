package com.example.magomed.motivateo.view.fragment;

public interface ISignUpFragment {
    boolean isEmpty();
    void startActivity(Class<?> cls);
    void errorSignIn();
    void popFragmentFromStack();
}
