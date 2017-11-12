package com.example.magomed.motivateo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.fragments.BaseFragment;
import com.example.magomed.motivateo.presenter.CreateTaskFragmentPresenterImpl;
import com.example.magomed.motivateo.presenter.ICreateTaskFragmentPresenter;

import static com.example.magomed.motivateo.net.utils.Constants.EVERYDAY;
import static com.example.magomed.motivateo.net.utils.Constants.EVERY_DAY_OFF;
import static com.example.magomed.motivateo.net.utils.Constants.EVERY_WEEKDAY;

public class SetRepeatTaskFragment extends BaseFragment{

    ICreateTaskFragmentPresenter presenter;

    public SetRepeatTaskFragment(){
        presenter = new CreateTaskFragmentPresenterImpl(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        View view = inflater.inflate(R.layout.fragment_repeat_task, container, false);
        view.findViewById(R.id.button_everyday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveRepeatForDefaultTask(EVERYDAY);
            }
        });
        view.findViewById(R.id.button_every_weekday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveRepeatForDefaultTask(EVERY_WEEKDAY);
            }
        });
        view.findViewById(R.id.button_every_weekday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveRepeatForDefaultTask(EVERY_DAY_OFF);
            }
        });

        view.findViewById(R.id.button_every_weekday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveRepeatForDefaultTask(EVERY_DAY_OFF);
            }
        });

        view.findViewById(R.id.button_arbitrarily).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.body_container, new ArbitrarilyRepeatTaskFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}
