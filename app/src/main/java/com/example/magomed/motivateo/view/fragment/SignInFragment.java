package com.example.magomed.motivateo.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.fragments.BaseFragment;
import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.net.utils.Constants;
import com.example.magomed.motivateo.presenter.AuthorizationFragmentPresenterImpl;
import com.example.magomed.motivateo.presenter.IAuthorizationFragmentPresenter;
import com.example.magomed.motivateo.presenter.ListenerHandler;
import com.example.magomed.motivateo.presenter.WelcomeFragmentPresenterImpl;

import java.util.Objects;

public class SignInFragment extends BaseFragment implements IAuthorizationFragment {
    IAuthorizationFragmentPresenter presenter;

    Button sign_in;

    EditText password;

    EditText login;

    ProgressBar progress;

    FragmentManager fragmentManager;

    private ListenerHandler<WelcomeFragmentPresenterImpl.OnUserGetListener> userHandler;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new AuthorizationFragmentPresenterImpl();
        presenter.onCreate(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        password = view.findViewById(R.id.sign_in_password);
        login = view.findViewById(R.id.sign_in_login);
        sign_in = view.findViewById(R.id.sign_in_button);
        progress = view.findViewById(R.id.progress);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView textViewInfo = view.findViewById(R.id.registration_text_view);
                if (isEmpty()) {
                    textViewInfo.setText(Constants.ERROR_DATA);
                } else {
                    startProgress();
                    if (userHandler != null) {
                        userHandler.unregister();
                    }
                    userHandler = presenter.signIn(new User(null, login.getText().toString(), password.getText().toString()), userListener);
                }
            }
        });
        return view;
    }

    @Override
    public void popFragmentFromStack() {
        fragmentManager.popBackStack();
    }

    public boolean isEmpty() {
        return (Objects.equals(password.getText().toString(), "") && Objects.equals(login.getText().toString(), ""));
    }

    @Override
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    @Override
    public void errorSignIn() {
        try {
            ((TextView)getActivity().findViewById(R.id.sign_in_text_view)).setText(Constants.ERROR_USER);
        }catch (NullPointerException e){
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    private void startProgress() {
        progress.setVisibility(View.VISIBLE);
        sign_in.setEnabled(false);
    }

    private void stopProgress() {
        progress.setVisibility(View.INVISIBLE);
        sign_in.setEnabled(true);
    }
}
