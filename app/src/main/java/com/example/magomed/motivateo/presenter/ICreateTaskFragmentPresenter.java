package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.models.Task;
import com.example.magomed.motivateo.view.fragment.CreateTaskFragment;
import com.example.magomed.motivateo.view.fragment.IListTaskFragmentView;

/**
 * Created by magomed on 09.11.17.
 */

public interface ICreateTaskFragmentPresenter {
    void onCreate(CreateTaskFragment view);
    void onResume();
    void loadTaskList();
    void sendTask(Task task);
}
