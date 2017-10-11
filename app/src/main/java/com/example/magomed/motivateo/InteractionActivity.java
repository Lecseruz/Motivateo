package com.example.magomed.motivateo;

import android.content.Intent;
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
    public String MENU = MENU_TASKS;
    public String STATE = STATE_TASKS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction);
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.inflateMenu(R.menu.menu_tasks);
        setSupportActionBar(myToolbar);
        ((Button)findViewById(R.id.state_task_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.body_container)
                if (body != null){
                    transaction.remove(body);
                }
                transaction.add(R.id.body_container, new )
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (MENU){
            case MENU_TASKS : {
                getMenuInflater().inflate(R.menu.menu_tasks, menu);
                break;
            }
            case MENU_SETTINGS: {
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
                Intent intent = new Intent(TodayFragment.this, ActivityAll.class);
                startActivity(intent);
                return true;

            case R.id.action_today:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_add:
                intent = new Intent(TodayFragment.this, SettingsFragment.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
