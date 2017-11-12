package com.example.magomed.motivateo.presenter;

import android.content.Context;

import com.example.magomed.motivateo.managers.DbManager;
import com.example.magomed.motivateo.models.Task;
import com.example.magomed.motivateo.net.utils.Constants;

/**
 * Created by magomed on 09.11.17.
 */

public class CreateTaskFragmentPresenterImpl implements ICreateTaskFragmentPresenter {
    private DbManager dbManager;

    public CreateTaskFragmentPresenterImpl(Context context){
        dbManager = DbManager.getInstance(context);
    }

    @Override
    public void createDefaultTask() {
        dbManager.insert(new Task(null));
    }

    @Override
    public void saveTimeForDefaultTask(Task task) {
        dbManager.updateTime(task);
    }

    @Override
    public void saveRepeatForDefaultTask(Task task) {
        dbManager.updateRepeat(task);
    }


}
