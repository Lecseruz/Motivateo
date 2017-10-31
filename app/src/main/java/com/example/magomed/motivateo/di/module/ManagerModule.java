package com.example.magomed.motivateo.di.module;

import android.support.annotation.NonNull;

import com.example.magomed.motivateo.managers.data.CredentialsManager;
import com.example.magomed.motivateo.managers.data.UserManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {
    @Singleton
    @NonNull
    @Provides
    public CredentialsManager provideCredentialsManager() {
        return new CredentialsManager();
    }


    @Singleton
    @NonNull
    @Provides
    public UserManager provideUserManager() {
        return new UserManager();
    }

}
