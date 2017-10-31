package com.example.magomed.motivateo.presenter;

import android.util.Log;

import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.service.ServiceFactory;
import com.example.magomed.motivateo.service.UserService;
import com.example.magomed.motivateo.view.activity.InteractionActivity;
import com.example.magomed.motivateo.view.fragment.ISignInFragment;
import com.example.magomed.motivateo.view.fragment.SignInFragment;
import com.example.magomed.motivateo.view.fragment.WelcomeFragment;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by magomed on 31.10.17.
 */

public class SignInFragmentPresenter implements ISignInPresenter {

    ISignInFragment signInFragment;


    @Override
    public void onCreate(ISignInFragment view) {
        this.signInFragment = view;
    }

    @Override
    public void signIn() {
        UserService service = ServiceFactory.createRetrofitService(UserService.class, UserService.SERVICE_ENDPOINT);

        User user = signInFragment.getUserInformation();
        service.signIn(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Message>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(Message response) {
                        if (response.getCode() == 200) {
                            signInFragment.startActivity(InteractionActivity.class);
                        } else {
                            signInFragment.errorSignIn();
                        }
                    }
                });
    }

}
