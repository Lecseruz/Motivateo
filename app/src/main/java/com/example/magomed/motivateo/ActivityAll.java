package com.example.magomed.motivateo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.magomed.motivateo.fragments.BaseFragment;


public class ActivityAll extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_all);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.all_toolbar);
//        myToolbar.inflateMenu(R.menu.menu_tasks);
//        setSupportActionBar(myToolbar);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tasks, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent intent;
//        switch (item.getItemId()) {
//            case R.id.action_all:
//
//                return true;
//
//            case R.id.action_today:
//                intent = new Intent(ActivityAll.this, MainActivity.class);
//                startActivity(intent);
//                return true;
//
//            case R.id.action_add:
//                intent = new Intent(ActivityAll.this, SettingsFragment.class);
//                startActivity(intent);
//                return true;
//
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
}