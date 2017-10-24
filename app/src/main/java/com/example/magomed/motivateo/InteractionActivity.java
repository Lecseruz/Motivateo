package com.example.magomed.motivateo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.example.magomed.motivateo.net.utils.Constants.*;

public class InteractionActivity extends AppCompatActivity{
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


        taskButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        transaction = fragmentManager.beginTransaction();
        Fragment body = fragmentManager.findFragmentById(R.id.body_container);
        if (body != null){
            transaction.remove(body);
        }
        transaction.add(R.id.body_container, new TaskFragment());
        transaction.commit();


        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.inflateMenu(R.menu.menu_authorization);
        setSupportActionBar(myToolbar);
        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myToolbar.setTitle(MENU_TASKS);
                taskButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                settingsButton.setTextColor(getResources().getColor(R.color.background_dark));
                shopButton.setTextColor(getResources().getColor(R.color.background_dark));
                transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.body_container);
                if (body != null){
                    transaction.remove(body);
                }
                transaction.add(R.id.body_container, new TaskFragment());
                transaction.commit();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myToolbar.setTitle(MENU_SETTINGS);
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
                transaction = fragmentManager.beginTransaction();
                myToolbar.setTitle(MENU_SHOP);
                taskButton.setTextColor(getResources().getColor(R.color.background_dark));
                shopButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                settingsButton.setTextColor(getResources().getColor(R.color.background_dark));
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
        getMenuInflater().inflate(R.menu.menu_authorization, menu);

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
