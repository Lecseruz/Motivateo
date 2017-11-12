package com.example.magomed.motivateo.view.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.fragments.BaseFragment;
import com.example.magomed.motivateo.presenter.ICreateTaskFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.magomed.motivateo.net.utils.Constants.TASK_COUNT_REPEAT;

public class CreateTaskFragment extends BaseFragment implements ICreateFragmentTask{
    @BindView(R.id.task_name)
    EditText task_name;

    private View.OnClickListener listenerCount;

    ICreateTaskFragmentPresenter presenter;

    @BindView(R.id.button_count)
    Button count;

    @BindView(R.id.button_save)
    Button save;

    FragmentManager fragmentManager;

    public CreateTaskFragment(){
        presenter.createDefaultTask();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_create_task, container, false);
        ButterKnife.bind(view);
        fragmentManager = getActivity().getSupportFragmentManager();
        view.findViewById(R.id.button_set_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new SetTimeTaskFragmnet())
                        .addToBackStack(null)
                        .commit();
            }
        });

        view.findViewById(R.id.button_repeat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, new SetRepeatTaskFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
