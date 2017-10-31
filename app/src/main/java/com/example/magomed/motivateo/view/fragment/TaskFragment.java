package com.example.magomed.motivateo.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.fragments.BaseFragment;

import static com.example.magomed.motivateo.net.utils.Constants.STATE_ALL;
import static com.example.magomed.motivateo.net.utils.Constants.STATE_TODAY;

public class TaskFragment extends BaseFragment {
    public String STATE = STATE_ALL;
    private Button buttonToday;
    private Button buttonAll;

    private FragmentManager fragmentManager;
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
                case R.id.navigation_all: {
                    STATE = STATE_ALL;
                    replaceFragment();
                    return true;
                }

                case R.id.navigation_today: {
                    STATE = STATE_TODAY;
                    replaceFragment();
                    return true;
                }
                default: break;

            }
            return false;
        }

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        fragmentManager = getFragmentManager();
        replaceFragment();
        BottomNavigationView navigation = view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return view;
    }

    public void replaceFragment() {
        switch (STATE) {
            case STATE_ALL: {
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.list_tasks);
                if (body != null) {
                    transaction.remove(body);
                }
                transaction.add(R.id.list_tasks, new AllFragment());
                transaction.commit();
                break;
            }

            case STATE_TODAY: {
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment body = fragmentManager.findFragmentById(R.id.list_tasks);
                if (body != null) {
                    transaction.remove(body);
                }
                transaction.add(R.id.list_tasks, new TodayFragment());
                transaction.commit();
                break;
            }

            default:
                break;
        }
    }
}
