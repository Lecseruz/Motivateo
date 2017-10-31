package com.example.magomed.motivateo.di.module;

import com.example.magomed.motivateo.presenter.MainActivityPresenterImpl;
import com.example.magomed.motivateo.view.activity.IMainActivityView;
import com.example.magomed.motivateo.view.activity.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by magomed on 29.10.17.
 */

@Module
public class MainActivityModule {
    private IMainActivityView view;

    public MainActivityModule(IMainActivityView view){
        this.view = view;
    }

    @Provides
    public MainActivityPresenterImpl provideMainActivityPresenterImpl() {
        return new MainActivityPresenterImpl(view);
    }
}
