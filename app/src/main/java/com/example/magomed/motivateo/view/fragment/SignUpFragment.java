package com.example.magomed.motivateo.view.fragment;

import android.content.Intent;
import android.os.Bundle;
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

public class SignUpFragment extends BaseFragment implements ISignUpFragment {
    IAuthorizationFragmentPresenter presenter;

    EditText email;

    EditText password;

    EditText login;

    ProgressBar progress;

    private Button sign_up;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        email = view.findViewById(R.id.registration_email);
        password = view.findViewById(R.id.registration_password);
        login = view.findViewById(R.id.registration_login);
        progress = view.findViewById(R.id.progress);
        sign_up = view.findViewById(R.id.registration_button);
        sign_up.setOnClickListener(new View.OnClickListener() {
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
                    userHandler = presenter.signUp(new User(email.getText().toString(), login.getText().toString(), password.getText().toString()), userListener);
                }
            }
        });
        return view;
    }

    @Override
    public boolean isEmpty() {
        return (Objects.equals(email.getText().toString(), "") && Objects.equals(password.getText().toString(), "") && Objects.equals(login.getText().toString(), ""));
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
            Log.e(Constants.ERROR_DATA, e.getMessage());
        }
    }

    private void startProgress() {
        progress.setVisibility(View.VISIBLE);
        sign_up.setEnabled(false);
    }

    private void stopProgress() {
        progress.setVisibility(View.INVISIBLE);
        sign_up.setEnabled(true);
    }
}
