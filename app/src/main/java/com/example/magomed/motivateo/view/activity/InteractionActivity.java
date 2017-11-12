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
import com.example.magomed.motivateo.view.fragment.CreateTaskFragment;
import com.example.magomed.motivateo.view.fragment.SettingsFragment;
import com.example.magomed.motivateo.view.fragment.ShopFragment;
import com.example.magomed.motivateo.view.fragment.TaskFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InteractionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    FragmentManager fragmentManager;

    @BindView(R.id.nav_view)
    NavigationView navigationView;
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
        ButterKnife.bind(this);

        toolbar.inflateMenu(R.menu.menu_authorization);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.body_container, new TaskFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tasks: {
                setTitle(getString(R.string.tasks));
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new TaskFragment())
                        .commit();
                break;
            }

            case R.id.create_task: {
                setTitle(getString(R.string.tasks));
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new CreateTaskFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            }

            case R.id.shop: {
                setTitle(getString(R.string.shop));
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new ShopFragment())
                        .commit();
                break;
            }

            case R.id.settings: {
                setTitle(getString(R.string.settings));
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new SettingsFragment())
                        .commit();
                break;
            }

            default: break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
