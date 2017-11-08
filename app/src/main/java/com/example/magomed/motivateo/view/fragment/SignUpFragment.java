package com.example.magomed.motivateo.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.app.App;
import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.presenter.ISignUpFragmentPresenter;
import com.example.magomed.motivateo.presenter.SignUpFragmentPresenter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment implements ISignUpFragment {
    ISignUpFragmentPresenter presenter;

    @BindView(R.id.registration_email)
    EditText email;

    @BindView(R.id.registration_password)
    EditText password;

    @BindView(R.id.registration_login)
    EditText login;

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
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new SignUpFragmentPresenter();
        presenter.onCreate(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(view);
        view.findViewById(R.id.registration_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView textViewInfo = (TextView) view.findViewById(R.id.registration_text_view);

                if (isEmpty()) {
                    textViewInfo.setText("Error data");
                } else {
                    presenter.signUp();
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
    public User getUserInformation() {
        return new User(email.getText().toString(), login.getText().toString(), password.getText().toString());
    }

    @Override
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        startActivity(intent);
    }

    @Override
    public void errorSignIn() {
        ((TextView) getView().findViewById(R.id.registration_text_view)).setText(" user not found");
    }
}
