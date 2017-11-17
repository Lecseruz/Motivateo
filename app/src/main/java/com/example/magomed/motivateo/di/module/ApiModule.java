package com.example.magomed.motivateo.di.module;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.magomed.motivateo.managers.data.UserManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {
    Context context;

    public ApiModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }

    @Provides
    @Singleton
    public Gson getGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    public Executor getExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    public Handler getMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

}
