package com.example.magomed.motivateo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.UserProfile;
import com.example.magomed.motivateo.app.App;
import com.example.magomed.motivateo.managers.data.CredentialsManager;
import com.example.magomed.motivateo.managers.data.UserManager;
import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.SocialUser;
import com.example.magomed.motivateo.service.SocialUserService;
import com.example.magomed.motivateo.service.UserService;
import com.example.magomed.motivateo.service.ServiceFactory;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private UserManager userManager = new UserManager();
    private CredentialsManager credentialsManager = new CredentialsManager();

    @Inject
    @NonNull
    Context context;

    @Inject
    Auth0 auth0;
    @Inject
    AuthenticationAPIClient authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getAppComponent().inject(this);

        Intent intent = new Intent(MainActivity.this, InteractionActivity.class);
        startActivity(intent);
//        signIn();

        findViewById(R.id.registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.sign_in_vk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        WebAuthProvider.init(auth0)
                .withScheme("https")
                .withConnection("vkontakte")
                .withAudience(String.format("https://%s/userinfo", getString(R.string.com_auth0_domain)))
                .start(MainActivity.this, new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull Dialog dialog) {
                        dialog.show();
                    }

                    @Override
                    public void onFailure(final AuthenticationException exception) {
                        //Show error to the user
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void signUp() {
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
                            Intent intent = new Intent(MainActivity.this, InteractionActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    private void signIn() {
        SocialUserService service = ServiceFactory.createRetrofitService(SocialUserService.class, UserService.SERVICE_ENDPOINT);

        String userId = userManager.getUserID(context);

        if (userId == null){
            Log.e(getClass().getSimpleName(), "You don't auentification");
            return;
        }
        service.signIn(new SocialUser(credentialsManager.getCredentials(context).getAccessToken(), userId))
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
                            Intent intent = new Intent(MainActivity.this, InteractionActivity.class);
                            startActivity(intent);
                        }
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

}
