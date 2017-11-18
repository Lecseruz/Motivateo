package com.example.magomed.motivateo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.presenter.IMainActivityPresenter;
import com.example.magomed.motivateo.presenter.MainActivityPresenterImpl;
import com.example.magomed.motivateo.view.fragment.WelcomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    IMainActivityPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenterImpl(this);
        fragmentManager = getSupportFragmentManager();

        if(isSignUp()) {
            startActivity(new Intent(MainActivity.this, InteractionActivity.class));
        }

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .replace(R.id.body_container, new WelcomeFragment())
                    .commit();
        }
    }

    public boolean isSignUp() {
        return presenter.isSignUp();
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
