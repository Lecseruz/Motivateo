package com.example.magomed.motivateo.app;

import android.app.Application;

import com.example.magomed.motivateo.di.components.AuthComponent;
import com.example.magomed.motivateo.di.module.ApiModule;
import com.example.magomed.motivateo.di.components.DaggerAuthComponent;


public class App extends Application{
    private static AuthComponent authComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        authComponent = buildComponent();
    }

    public static AuthComponent getAppComponent() {
        return authComponent;
    }

    protected AuthComponent buildComponent() {
        return DaggerAuthComponent.builder()
                .apiModule(new ApiModule(getApplicationContext()))
                .build();
    }
}
