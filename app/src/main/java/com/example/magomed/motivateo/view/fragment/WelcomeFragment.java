package com.example.magomed.motivateo.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.app.App;
import com.example.magomed.motivateo.presenter.WelcomeFragmentPresenterImpl;

import javax.inject.Inject;

public class WelcomeFragment extends Fragment implements IWelcomeFragment{

    @Inject
    WelcomeFragmentPresenterImpl presenter;
    private FragmentManager fragmentManager;

    private AppCompatActivity activity;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (AppCompatActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter.onCreate(this);
        this.fragmentManager = getFragmentManager();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        view.findViewById(R.id.registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new SignUpFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        view.findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new SignInFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        view.findViewById(R.id.sign_in_vk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        return view;
    }

    @Override
    public void login() {
        presenter.login();
    }

    @Override
    public boolean isSignUp() {
        return presenter.isSignUp();
    }

    @Override
    public void signUp() {
        presenter.signUp();
    }

    @Override
    public void startActivity(Class<?> cls){
        Intent intent = new Intent(activity, cls);
        startActivity(intent);
    }

    @Override
    public Activity getMainActivity() {
        return getActivity();
    }
}
