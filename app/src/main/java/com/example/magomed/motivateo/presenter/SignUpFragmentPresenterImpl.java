package com.example.magomed.motivateo.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.magomed.motivateo.app.App;
import com.example.magomed.motivateo.di.components.AuthComponent;
import com.example.magomed.motivateo.managers.data.UserManager;
import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.net.utils.Constants;
import com.example.magomed.motivateo.service.UserService;
import com.example.magomed.motivateo.view.activity.InteractionActivity;
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


public class SignUpFragmentPresenterImpl implements ISignUpFragmentPresenter {

    @Inject
    UserManager userManager;

    @Inject
    Gson GSON;

    @Inject
    Executor executor;

    @Inject
    Context context;

    private AuthComponent component;

    private final UserService service;

    @Inject
    Handler mainHandler;

    public SignUpFragmentPresenterImpl() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UserService.class);
        if (component == null) {
            component = App.getAppComponent();
        }
        component.inject(this);
    }


    @Override
    public ListenerHandler<WelcomeFragmentPresenterImpl.OnUserGetListener> signUp(final User user, WelcomeFragmentPresenterImpl.OnUserGetListener listener) {
        final ListenerHandler<WelcomeFragmentPresenterImpl.OnUserGetListener> handler = new ListenerHandler<>(listener);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Response<ResponseBody> response = service.signUp(user).execute();
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
                        userManager.saveUserEmail(context, user.getEmail());
                        invokeSuccess(handler);
                    }
                } catch (IOException e) {
                    invokeError(handler, e);
                }
            }
        });
        return handler;
    }

    private void invokeSuccess(final ListenerHandler<WelcomeFragmentPresenterImpl.OnUserGetListener> handler) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                WelcomeFragmentPresenterImpl.OnUserGetListener listener = handler.getListener();
                if (listener != null) {
                    Log.d("API", "listener NOT null");
                    listener.onUserSuccess(InteractionActivity.class);
                } else {
                    Log.d("API", "listener is null");
                }
            }
        });
    }

    private void invokeError(final ListenerHandler<WelcomeFragmentPresenterImpl.OnUserGetListener> handler, final Exception error) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                WelcomeFragmentPresenterImpl.OnUserGetListener listener = handler.getListener();
                if (listener != null) {
                    Log.d("API", "listener NOT null");
                    listener.onUserError(error);
                } else {
                    Log.d("API", "listener is null");
                }
            }
        });
    }

    private Message<User> parseUser(final String body) throws IOException {
        try {
            Type type = new TypeToken<Message<User>>() {
            }.getType();
            return GSON.fromJson(body, type);
        } catch (JsonSyntaxException e) {
            throw new IOException(e);
        }
    }
}
