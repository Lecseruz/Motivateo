package com.example.magomed.motivateo.presenter;

import android.util.Log;

import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.service.ServiceFactory;
import com.example.magomed.motivateo.service.UserService;
import com.example.magomed.motivateo.view.activity.InteractionActivity;
import com.example.magomed.motivateo.view.fragment.ISignUpFragment;
import com.example.magomed.motivateo.view.fragment.SignUpFragment;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by magomed on 31.10.17.
 */

public class SignUpFragmentPresenter implements ISignUpFragmentPresenter {
    private ISignUpFragment view;

    @Override
    public void signUp() {
        UserService service = ServiceFactory.createRetrofitService(UserService.class, UserService.SERVICE_ENDPOINT);

        User user = view.getUserInformation();
        service.signUp(user)
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
                            view.startActivity(InteractionActivity.class);
                        } else {
                            view.errorSignIn();
                        }
                    }
                });
    }

    @Override
    public void onCreate(ISignUpFragment view) {
        this.view = view;
    }
}
