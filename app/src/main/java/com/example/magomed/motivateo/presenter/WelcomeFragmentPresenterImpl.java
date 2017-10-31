package com.example.magomed.motivateo.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.UserProfile;
import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.app.App;
import com.example.magomed.motivateo.managers.data.CredentialsManager;
import com.example.magomed.motivateo.managers.data.UserManager;
import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.SocialUser;
import com.example.magomed.motivateo.service.ServiceFactory;
import com.example.magomed.motivateo.service.SocialUserService;
import com.example.magomed.motivateo.service.UserService;
import com.example.magomed.motivateo.view.activity.InteractionActivity;
import com.example.magomed.motivateo.view.fragment.IWelcomeFragment;
import com.example.magomed.motivateo.view.fragment.WelcomeFragment;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WelcomeFragmentPresenterImpl implements IWelcomeFragmentPresenter {
    @Inject
    CredentialsManager credentialsManager;

    @Inject
    UserManager userManager;

    @Inject
    @NonNull
    Context context;

    @Inject
    Auth0 auth0;

    @Inject
    AuthenticationAPIClient authentication;

    private IWelcomeFragment fragment;


    public WelcomeFragmentPresenterImpl(){
        App.getAppComponent().inject(this);
    }

    @Override
    public void login() {
        WebAuthProvider.init(auth0)
                .withScheme("https")
                .withConnection("vkontakte")
                .withAudience(String.format("https://%s/userinfo", fragment.getMainActivity().getString(R.string.com_auth0_domain)))
                .start(fragment.getMainActivity(), new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull Dialog dialog) {
                        dialog.show();
                    }

                    @Override
                    public void onFailure(final AuthenticationException exception) {
                        //Show error to the user
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(String.valueOf(this.getClass()), exception.getMessage());
                            }
                        });

                    }

                    @Override
                    public void onSuccess(@NonNull Credentials credentials) {
                        credentialsManager.saveCredentials(context, credentials);
                        saveUserProfile();
                        signUp();
                    }
                });
    }

    public void saveUserProfile() {
        Log.d("accessToken", credentialsManager.getCredentials(context).getAccessToken());
        authentication
                .userInfo(credentialsManager.getCredentials(context).getAccessToken())
                .start(new BaseCallback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(UserProfile userProfile) {
                        userManager.saveUserID(context, userProfile.getId());
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        ;
                    }
                });
    }

    @Override
    public boolean isSignUp() {
        return userManager.getUserID(context) == null;
    }

    @Override
    public void signUp() {
        SocialUserService service = ServiceFactory.createRetrofitService(SocialUserService.class, UserService.SERVICE_ENDPOINT);

        String userId = userManager.getUserID(context);

        if (userId == null){
            Log.e(getClass().getSimpleName(), "You don't auentification");
            return;
        }

        service.signUp(new SocialUser(credentialsManager.getCredentials(context).getAccessToken(), userId))
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
                            fragment.startActivity(InteractionActivity.class);
                        }
                    }
                });
    }


    @Override
    public void onCreate(WelcomeFragment view) {
        this.fragment = view;
    }

    @Override
    public void onPause() {
    }
}
