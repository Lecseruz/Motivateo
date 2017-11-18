package com.example.magomed.motivateo.presenter;


import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
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
import com.example.magomed.motivateo.di.components.AuthComponent;
import com.example.magomed.motivateo.managers.data.CredentialsManager;
import com.example.magomed.motivateo.managers.data.UserManager;
import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.SocialUser;
import com.example.magomed.motivateo.net.utils.Constants;
import com.example.magomed.motivateo.service.SocialUserService;
import com.example.magomed.motivateo.view.activity.InteractionActivity;
import com.example.magomed.motivateo.view.fragment.IWelcomeFragment;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WelcomeFragmentPresenterImpl implements IWelcomeFragmentPresenter {
    @Inject
    CredentialsManager credentialsManager;

    @Inject
    UserManager userManager;

    @Inject
    Context context;

    @Inject
    Gson GSON;

    @Inject
    Handler mainHandler;

    @Inject
    Executor executor;

    @Inject
    Auth0 auth0;

    @Inject
    AuthenticationAPIClient authentication;

    private AuthComponent component;


    private IWelcomeFragment fragment;

    private final SocialUserService service;


    public WelcomeFragmentPresenterImpl() {
        App.getAppComponent().inject(this);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SocialUserService.class);
        if (component == null) {
            component = App.getAppComponent();
        }
        component.inject(this);
    }

    @Override
    public void login(final OnUserGetListener userListener) {
        WebAuthProvider.init(auth0)
                .withScheme(Constants.HTTPS)
                .withConnection(Constants.VK)
                .withAudience(String.format(Constants.ENDPOINT_AUTH0, fragment.getMainActivity().getString(R.string.com_auth0_domain)))
                .start(fragment.getMainActivity(), new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull Dialog dialog) {
                        dialog.show();
                    }

                    @Override
                    public void onFailure(final AuthenticationException exception) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(String.valueOf(this.getClass()), exception.getMessage());
                            }
                        });

                    }

                    @Override
                    public void onSuccess(@NonNull Credentials credentials) {
                        credentialsManager.saveCredentials(credentials);
                        saveUserProfile();
                        try {
                            signUp(userListener);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void saveUserProfile() {
        Log.d("accessToken", credentialsManager.getCredentials().getAccessToken());
        String accessToken = credentialsManager.getCredentials().getAccessToken();
        if (accessToken == null) {
            Log.d(Constants.ERROR_DATA, Constants.ERROR_CREDENTIALS);
            return;
        }
        authentication
                .userInfo(accessToken)
                .start(new BaseCallback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(UserProfile userProfile) {
                        userManager.saveUserID(userProfile.getId());
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {

                    }
                });
    }

    @Override
    public void signUp(final OnUserGetListener userListener) throws IOException {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String userId = userManager.getUserID();

                    if (userId == null) {
                        throw new IOException(Constants.ERROR_AUTH);
                    }
                    Response<ResponseBody> response = service.signUp(new SocialUser(credentialsManager.getCredentials().getAccessToken(), userId)).execute();
                    if (response.code() != 200) {
                        throw new IOException("HTTP code " + response.code());
                    }
                    try (final ResponseBody responseBody = response.body()) {
                        if (responseBody == null) {
                            throw new IOException("Cannot get body");
                        }
                        if (parseUser(response.body().string()).getCode() != 200) {
                            throw new IOException(Constants.ERROR_USER);
                        }
                        userManager.saveUserID(userId);
                        invokeSuccess(userListener);
                    }
                } catch (IOException e) {
                    invokeError(userListener, e);
                }

            }
        });
    }


    private Message<SocialUser> parseUser(final String body) throws IOException {
        try {
            Type type = new TypeToken<Message<SocialUser>>() {}.getType();
            return GSON.fromJson(body, type);
        } catch (JsonSyntaxException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void onCreate(IWelcomeFragment view) {
        this.fragment = view;
        App.getAppComponent().inject(this);
    }

    public interface OnUserGetListener {
        void onUserSuccess(Class<?> cls);

        void onUserError(final Exception error);
    }

    private void invokeSuccess(final OnUserGetListener listener) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    Log.d("API", "listener NOT null");
                    listener.onUserSuccess(InteractionActivity.class);
                } else {
                    Log.d("API", "listener is null");
                }
            }
        });
    }

    private void invokeError(final OnUserGetListener listener, final Exception error) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    Log.d("API", "listener NOT null");
                    listener.onUserError(error);
                } else {
                    Log.d("API", "listener is null");
                }
            }
        });
    }
}
