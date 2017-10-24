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
    private Button settingsButton;
    private Button shopButton;
    private Button taskButton;
    private FragmentManager fragmentManager;
    private Toolbar myToolbar;
    private String STATE = STATE_TASKS;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.state_task_button: {
                    STATE = STATE_TASKS;
                    myToolbar.setTitle(MENU_TASKS);
                    replaceColorForButtons();
                    replaceFragment();
                    break;
                }

                case R.id.state_shop_button: {
                    STATE = STATE_SHOP;
                    myToolbar.setTitle(MENU_SHOP);
                    replaceColorForButtons();
                    replaceFragment();
                    break;
                }

                case R.id.state_settings_button: {
                    STATE = STATE_SETTINGS;
                    myToolbar.setTitle(MENU_SETTINGS);
                    replaceColorForButtons();
                    replaceFragment();
                    break;
                }

                default: break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction);
        settingsButton = (Button)findViewById(R.id.state_settings_button);
        shopButton = (Button)findViewById(R.id.state_shop_button);
        taskButton = (Button)findViewById(R.id.state_task_button);
        fragmentManager = getSupportFragmentManager();
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.inflateMenu(R.menu.menu_authorization);
        setSupportActionBar(myToolbar);
        taskButton.setOnClickListener(clickListener);
        settingsButton.setOnClickListener(clickListener);
        shopButton.setOnClickListener(clickListener);
        replaceColorForButtons();
        replaceFragment();
    }

    public void replaceFragment(){
        switch(STATE){
            case STATE_TASKS: {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.body_container);
                if (body != null){
                    transaction.remove(body);
                }
                transaction.add(R.id.body_container, new TaskFragment());
                transaction.commit();
                break;
            }

            case STATE_SHOP: {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.body_container);
                if (body != null){
                    transaction.remove(body);
                }
                transaction.add(R.id.body_container, new ShopFragment());
                transaction.commit();
            }

            case STATE_SETTINGS: {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.body_container);
                if (body != null){
                    transaction.remove(body);
                }
                transaction.add(R.id.body_container, new SettingsFragment());
                transaction.commit();
            }

            default: break;
        }
    }

    public void replaceColorForButtons(){
        switch(STATE){
            case STATE_TASKS: {
                taskButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                settingsButton.setTextColor(getResources().getColor(R.color.background_dark));
                shopButton.setTextColor(getResources().getColor(R.color.background_dark));
                break;
            }

            case STATE_SHOP: {
                taskButton.setTextColor(getResources().getColor(R.color.background_dark));
                shopButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                settingsButton.setTextColor(getResources().getColor(R.color.background_dark));
                break;
            }

            case STATE_SETTINGS: {
                taskButton.setTextColor(getResources().getColor(R.color.background_dark));
                settingsButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                shopButton.setTextColor(getResources().getColor(R.color.background_dark));
                break;
            }

            default: break;
        }
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
