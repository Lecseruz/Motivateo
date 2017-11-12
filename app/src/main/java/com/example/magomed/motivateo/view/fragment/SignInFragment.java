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
import com.example.magomed.motivateo.fragments.BaseFragment;
import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.presenter.SignInFragmentPresenter;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignInFragment extends BaseFragment implements ISignInFragment{
    SignInFragmentPresenter presenter;

    private AppCompatActivity activity;

    @BindView(R.id.sign_in_password)
    EditText password;

    @BindView(R.id.sign_in_login)
    EditText login;

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
        presenter = new SignInFragmentPresenter();
        presenter.onCreate(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(view);
        view.findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final TextView textViewInfo = view.findViewById(R.id.sign_in_text_view);

                if (isEmpty()) {
                    textViewInfo.setText("Error data");
                } else {
                    presenter.signIn();
                }
            }
        });
        return view;
    }

    public boolean isEmpty() {
        return (Objects.equals(password.getText().toString(), "") && Objects.equals(login.getText().toString(), ""));
    }

    @Override
    public User getUserInformation() {
        return new User(null, login.getText().toString(), password.getText().toString());
    }

    @Override
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        startActivity(intent);
    }

    @Override
    public void errorSignIn() {
        ((TextView)getView().findViewById(R.id.sign_in_text_view)).setText(" user not found");
    }
}
