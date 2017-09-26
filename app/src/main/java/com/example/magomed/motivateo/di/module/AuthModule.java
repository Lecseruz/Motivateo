package com.example.magomed.motivateo.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthModule {

    @Singleton
    @NonNull
    @Provides
    public Auth0 provideAuth0(Context context) {
        return new Auth0(context);
    }


    @Singleton
    @NonNull
    @Provides
    public AuthenticationAPIClient provideAuthClient(Auth0 auth0) {
        return new AuthenticationAPIClient(auth0);
    }
}
