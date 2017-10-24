package com.example.magomed.motivateo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.magomed.motivateo.fragments.BaseFragment;

import static com.example.magomed.motivateo.net.utils.Constants.STATE_ALL;
import static com.example.magomed.motivateo.net.utils.Constants.STATE_TODAY;

public class TaskFragment extends BaseFragment {
    public  String STATE = STATE_ALL;
    private Button buttonToday;
    private Button buttonAll;

    private FragmentManager fragmentManager;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_all: {
                    STATE = STATE_ALL;
                    replaceFragment();
                    replaceColorForButtons();
                    break;
                }

                case R.id.button_today: {
                    STATE = STATE_TODAY;
                    replaceFragment();
                    replaceColorForButtons();
                    break;
                }

                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        fragmentManager = getFragmentManager();
        buttonAll = view.findViewById(R.id.button_all);
        buttonToday = view.findViewById(R.id.button_today);
        buttonAll.setOnClickListener(clickListener);
        buttonToday.setOnClickListener(clickListener);
        replaceFragment();
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

    public void replaceColorForButtons() {
        switch (STATE) {
            case STATE_TODAY: {
                buttonToday.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                buttonAll.setTextColor(getResources().getColor(R.color.background_dark));
                break;
            }

            case STATE_ALL: {
                buttonAll.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                buttonToday.setTextColor(getResources().getColor(R.color.background_dark));
                break;
            }

        }
    }
}
