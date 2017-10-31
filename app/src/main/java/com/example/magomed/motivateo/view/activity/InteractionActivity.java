package com.example.magomed.motivateo.view.activity;

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

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.view.fragment.SettingsFragment;
import com.example.magomed.motivateo.view.fragment.ShopFragment;
import com.example.magomed.motivateo.view.fragment.TaskFragment;
import com.example.magomed.motivateo.view.fragment.CreateTaskFragment;

import static com.example.magomed.motivateo.net.utils.Constants.MENU_SETTINGS;
import static com.example.magomed.motivateo.net.utils.Constants.MENU_SHOP;
import static com.example.magomed.motivateo.net.utils.Constants.MENU_TASKS;

public class InteractionActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Toolbar myToolbar;
    private String MENU = MENU_TASKS;

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
                    MENU = MENU_TASKS;
                    myToolbar.inflateMenu(R.menu.menu_tasks);
                    setSupportActionBar(myToolbar);
                    transaction.add(R.id.body_container, new TaskFragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_shop:
                    myToolbar.setTitle(MENU_SHOP);
                    MENU = MENU_SHOP;
                    myToolbar.inflateMenu(R.menu.menu_authorization);
                    setSupportActionBar(myToolbar);
                    transaction.add(R.id.body_container, new ShopFragment());
                    transaction.commit();

                    return true;
                case R.id.navigation_settings:
                    myToolbar.setTitle(MENU_SETTINGS);
                    MENU = MENU_SETTINGS;
                    myToolbar.inflateMenu(R.menu.menu_authorization);
                    setSupportActionBar(myToolbar);
                    transaction.add(R.id.body_container, new SettingsFragment());
                    transaction.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.body_container, new TaskFragment())
                .commit();
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.inflateMenu(R.menu.menu_tasks);
        setSupportActionBar(myToolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (MENU){
            case MENU_TASKS: {
                getMenuInflater().inflate(R.menu.menu_tasks, menu);
                return true;
            }
            default: {
                getMenuInflater().inflate(R.menu.menu_authorization, menu);
                return true;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add: {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.body_container);
                if (body != null) {
                    transaction.remove(body);
                }
                transaction.add(R.id.body_container, new CreateTaskFragment());
                transaction.commit();
                break;
            }
            default: break;
        }
        return true;
    }
}
