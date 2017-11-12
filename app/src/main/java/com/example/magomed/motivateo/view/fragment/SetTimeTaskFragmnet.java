package com.example.magomed.motivateo.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.fragments.BaseFragment;
import com.example.magomed.motivateo.presenter.CreateTaskFragmentPresenterImpl;
import com.example.magomed.motivateo.presenter.ICreateTaskFragmentPresenter;

import butterknife.BindView;


public class SetTimeTaskFragmnet extends BaseFragment {
    @BindView(R.id.timePicker)
    TimePicker timePicker;

    private ICreateTaskFragmentPresenter presenter;


    public SetTimeTaskFragmnet(){
        presenter = new CreateTaskFragmentPresenterImpl(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_time_task, container, false);
        view.findViewById(R.id.button_save_time).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                presenter.saveTimeForDefaultTask(timePicker.getHour(), timePicker.getMinute());
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
}
