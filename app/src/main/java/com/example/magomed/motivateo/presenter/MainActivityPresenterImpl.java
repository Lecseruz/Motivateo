package com.example.magomed.motivateo.presenter;

import android.content.Context;

import com.example.magomed.motivateo.app.App;
import com.example.magomed.motivateo.di.components.AuthComponent;
import com.example.magomed.motivateo.managers.data.UserManager;
import com.example.magomed.motivateo.net.utils.Constants;
import com.example.magomed.motivateo.view.activity.IMainActivityView;

import javax.inject.Inject;

public class MainActivityPresenterImpl implements IMainActivityPresenter {
    private IMainActivityView view;

    @Inject
    UserManager manager;

    @Inject
    Context context;

    private AuthComponent component;

    @Override
    public void onBackPressed() {
        view.popFragmentFromStack();
    }

    @Override
    public boolean isSignUp() {
        return manager.getUserID(context) != null || manager.getUserEmail(context) != null;
    }

    public MainActivityPresenterImpl(IMainActivityView view){
        if (component == null) {
            component = App.getAppComponent();
        }
        component.inject(this);
        this.view = view;
    }
}
