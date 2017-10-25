package com.example.magomed.motivateo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static com.example.magomed.motivateo.net.utils.Constants.MENU_SETTINGS;
import static com.example.magomed.motivateo.net.utils.Constants.MENU_SHOP;
import static com.example.magomed.motivateo.net.utils.Constants.MENU_TASKS;

public class InteractionActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Toolbar myToolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment body = fragmentManager.findFragmentById(R.id.body_container);
            if (body != null) {
                transaction.remove(body);
            }
            switch (item.getItemId()) {
                case R.id.navigation_tasks:
                    myToolbar.setTitle(MENU_TASKS);
                    transaction.add(R.id.body_container, new TaskFragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_shop:
                    myToolbar.setTitle(MENU_SHOP);
                    transaction.add(R.id.body_container, new ShopFragment());
                    transaction.commit();

                    return true;
                case R.id.navigation_settings:
                    myToolbar.setTitle(MENU_SETTINGS);
                    transaction.add(R.id.body_container, new SettingsFragment());
                    transaction.commit();
                    return true;
            }
            return false;
        }

    };

    public InteractionActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction);
        fragmentManager = getSupportFragmentManager();
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.inflateMenu(R.menu.menu_authorization);
        setSupportActionBar(myToolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_authorization, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
