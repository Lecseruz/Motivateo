package com.example.magomed.motivateo.di.components;

import com.example.magomed.motivateo.MainActivity;
import com.example.magomed.motivateo.di.module.ApiModule;
import com.example.magomed.motivateo.di.module.AuthModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules =
        {
                ApiModule.class,
                AuthModule.class
        }
)
public interface AuthComponent {
    void inject(MainActivity mainActivity);
}
