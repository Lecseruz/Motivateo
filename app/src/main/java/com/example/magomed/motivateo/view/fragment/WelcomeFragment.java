package com.example.magomed.motivateo.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.app.App;
import com.example.magomed.motivateo.fragments.BaseFragment;
import com.example.magomed.motivateo.presenter.IWelcomeFragmentPresenter;
import com.example.magomed.motivateo.presenter.WelcomeFragmentPresenterImpl;

public class WelcomeFragment extends BaseFragment implements IWelcomeFragment {

    IWelcomeFragmentPresenter presenter;

    Button vk;

    Button sign_in;

    ProgressBar progress;

    Button sign_up;

    private WelcomeFragmentPresenterImpl.OnUserGetListener userListener = new WelcomeFragmentPresenterImpl.OnUserGetListener()
    {
        @Override
        public void onUserSuccess(Class<?> cls) {
            startActivity(cls);
            stopProgress();
        }

        @Override
        public void onUserError(final Exception error) {
            stopProgress();
            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new WelcomeFragmentPresenterImpl();
        presenter.onCreate(this);
        this.fragmentManager = getActivity().getSupportFragmentManager();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        sign_up = view.findViewById(R.id.registration);
        vk = view.findViewById(R.id.sign_in_vk);
        sign_in = view.findViewById(R.id.sign_in);
        progress = view.findViewById(R.id.progress);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new SignUpFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new SignInFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProgress();
                presenter.login(userListener);
            }
        });
        return view;
    }

    @Override
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    @Override
    public Activity getMainActivity() {
        return getActivity();
    }


    private void startProgress() {
        progress.setVisibility(View.VISIBLE);
        vk.setEnabled(false);
        sign_in.setEnabled(false);
        sign_up.setEnabled(false);
    }

    private void stopProgress() {
        progress.setVisibility(View.INVISIBLE);
        vk.setEnabled(true);
        sign_in.setEnabled(true);
        sign_up.setEnabled(true);
    }
}
