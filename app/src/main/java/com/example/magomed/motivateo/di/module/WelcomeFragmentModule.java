package com.example.magomed.motivateo.di.module;

import android.support.annotation.NonNull;

import com.example.magomed.motivateo.managers.data.CredentialsManager;
import com.example.magomed.motivateo.managers.data.UserManager;
import com.example.magomed.motivateo.presenter.WelcomeFragmentPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by magomed on 31.10.17.
 */

@Module
public class WelcomeFragmentModule {
    @Singleton
    @NonNull
    @Provides
    public WelcomeFragmentPresenterImpl provideWelcomeFragmentPresenterImpl() {
        return new WelcomeFragmentPresenterImpl();
    }

}