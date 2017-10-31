package com.example.magomed.motivateo.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.view.fragment.TaskFragment;

public class InteractionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fragmentManager;
    private Toolbar myToolbar;
//    private String MENU = MENU_TASKS;
//
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            Fragment body = fragmentManager.findFragmentById(R.id.body_container);
//            if (body != null) {
//                transaction.remove(body);
//            }
//            switch (item.getItemId()) {
//                case R.id.navigation_tasks:
//                    myToolbar.setTitle(MENU_TASKS);
//                    MENU = MENU_TASKS;
//                    myToolbar.inflateMenu(R.menu.menu_tasks);
//                    setSupportActionBar(myToolbar);
//                    transaction.add(R.id.body_container, new TaskFragment());
//                    transaction.commit();
//                    return true;
//                case R.id.navigation_shop:
//                    myToolbar.setTitle(MENU_SHOP);
//                    MENU = MENU_SHOP;
//                    myToolbar.inflateMenu(R.menu.menu_authorization);
//                    setSupportActionBar(myToolbar);
//                    transaction.add(R.id.body_container, new ShopFragment());
//                    transaction.commit();
//
//                    return true;
//                case R.id.navigation_settings:
//                    myToolbar.setTitle(MENU_SETTINGS);
//                    MENU = MENU_SETTINGS;
//                    myToolbar.inflateMenu(R.menu.menu_authorization);
//                    setSupportActionBar(myToolbar);
//                    transaction.add(R.id.body_container, new SettingsFragment());
//                    transaction.commit();
//                    return true;
//            }
//            return false;
//        }
//
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_drawer);
//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.body_container, new TaskFragment())
//                .commit();
//        myToolbar = (Toolbar) findViewById(R.id.toolbar);
//        myToolbar.inflateMenu(R.menu.menu_tasks);
//        setSupportActionBar(myToolbar);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        switch (MENU){
//            case MENU_TASKS: {
//                getMenuInflater().inflate(R.menu.menu_tasks, menu);
//                return true;
//            }
//            default: {
//                getMenuInflater().inflate(R.menu.menu_authorization, menu);
//                return true;
//            }
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_add: {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                Fragment body = fragmentManager.findFragmentById(R.id.body_container);
//                if (body != null) {
//                    transaction.remove(body);
//                }
//                transaction.add(R.id.body_container, new CreateTaskFragment());
//                transaction.commit();
//                break;
//            }
//            default: break;
//        }
//        return true;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.inflateMenu(R.menu.menu_authorization);
        setSupportActionBar(myToolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.body_container, new TaskFragment())
                .commit();
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
