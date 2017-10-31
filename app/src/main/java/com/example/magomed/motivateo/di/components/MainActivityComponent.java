package com.example.magomed.motivateo.di.components;

import com.example.magomed.motivateo.di.module.MainActivityModule;
import com.example.magomed.motivateo.view.activity.MainActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by magomed on 30.10.17.
 */


@Singleton
@Component(modules =
        {
                MainActivityModule.class,
        }
)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
