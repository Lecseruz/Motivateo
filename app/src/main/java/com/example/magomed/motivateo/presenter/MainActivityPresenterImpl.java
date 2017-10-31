package com.example.magomed.motivateo.presenter;

import android.provider.ContactsContract;

import com.example.magomed.motivateo.view.activity.IMainActivityView;

import javax.inject.Inject;

/**
 * Created by magomed on 29.10.17.
 */

public class MainActivityPresenterImpl implements IMainActivityPresenter {
    private IMainActivityView view;

    @Override
    public void onBackPressed() {
        view.popFragmentFromStack();
    }

    @Inject
    public MainActivityPresenterImpl(IMainActivityView view){
        this.view = view;
    }
}
