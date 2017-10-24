package com.example.magomed.motivateo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.magomed.motivateo.adapters.TaskPagerAdapter;

import static com.example.magomed.motivateo.net.utils.Constants.*;

public class InteractionActivity extends AppCompatActivity{
    public String MENU = MENU_TASKS;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction);
        final Button settingsButton = (Button)findViewById(R.id.state_settings_button);
        final Button shopButton = (Button)findViewById(R.id.state_shop_button);
        final Button taskButton = (Button)findViewById(R.id.state_task_button);
        fragmentManager = getSupportFragmentManager();
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.inflateMenu(R.menu.menu_tasks);
        setSupportActionBar(myToolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TaskPagerAdapter(this));
        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MENU = MENU_TASKS;
                myToolbar.inflateMenu(R.menu.menu_authorization);
                myToolbar.setTitle("");
                taskButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                settingsButton.setTextColor(getResources().getColor(R.color.background_dark));
                shopButton.setTextColor(getResources().getColor(R.color.background_dark));

                setSupportActionBar(myToolbar);
                transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.body_container);
                if (body != null){
                    transaction.remove(body);
                }
                transaction.add(R.id.body_container, new TodayFragment());
                transaction.commit();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MENU = MENU_SETTINGS;
                myToolbar.inflateMenu(R.menu.menu_authorization);
                myToolbar.setTitle(MENU_SETTINGS);
                setSupportActionBar(myToolbar);
                taskButton.setTextColor(getResources().getColor(R.color.background_dark));
                settingsButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                shopButton.setTextColor(getResources().getColor(R.color.background_dark));

                transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.body_container);
                if (body != null){
                    transaction.remove(body);
                }
                transaction.add(R.id.body_container, new SettingsFragment());
                transaction.commit();
            }
        });
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MENU = MENU_SHOP;
                transaction = fragmentManager.beginTransaction();
                myToolbar.inflateMenu(R.menu.menu_authorization);
                myToolbar.setTitle(MENU_SHOP);
                taskButton.setTextColor(getResources().getColor(R.color.background_dark));
                shopButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                settingsButton.setTextColor(getResources().getColor(R.color.background_dark));
                setSupportActionBar(myToolbar);
                Fragment body = fragmentManager.findFragmentById(R.id.body_container);
                if (body != null){
                    transaction.remove(body);
                }
                transaction.add(R.id.body_container, new ShopFragment());
                transaction.commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (MENU){
            case MENU_TASKS : {
                setTitle("");
                getMenuInflater().inflate(R.menu.menu_tasks, menu);
                break;
            }
            case MENU_SETTINGS: {
                setTitle(MENU_SETTINGS);
                getMenuInflater().inflate(R.menu.menu_authorization, menu);
                break;
            }
            case MENU_SHOP: {
                getMenuInflater().inflate(R.menu.menu_authorization, menu);
                break;
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_all:

            case R.id.action_today:

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
