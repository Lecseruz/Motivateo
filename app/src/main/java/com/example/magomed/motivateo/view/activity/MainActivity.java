package com.example.magomed.motivateo.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.di.components.DaggerMainActivityComponent;
import com.example.magomed.motivateo.di.components.MainActivityComponent;
import com.example.magomed.motivateo.di.module.MainActivityModule;
import com.example.magomed.motivateo.presenter.MainActivityPresenterImpl;
import com.example.magomed.motivateo.view.fragment.WelcomeFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    @Inject
    MainActivityPresenterImpl presenter;

    private MainActivityComponent component;
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


//        startActivity(new Intent(MainActivity.this, InteractionActivity.class));


        setSupportActionBar(toolbar);
        setupComponent();
        Fragment fragment = fragmentManager.findFragmentById(R.layout.fragment_welcome);
        if (fragment == null){
            fragment = new WelcomeFragment();
        }
        if (savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .replace(R.id.body_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            presenter.onBackPressed();
        } else {
            super.onBackPressed();
        }
        return true;
    }

    protected void setupComponent() {
        if (component == null) {
            component = DaggerMainActivityComponent.builder()
                    .mainActivityModule(new MainActivityModule(this))
                    .build();
        }
        component.inject(this);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            presenter.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void popFragmentFromStack() {
        fragmentManager.popBackStack();

    }
}
