package com.example.magomed.motivateo.presenter;

/**
 * Created by magomed on 09.11.17.
 */

public interface ICreateTaskFragmentPresenter {
    void createDefaultTask();
    void saveTimeForDefaultTask(int hour, int minute);
    void saveRepeatForDefaultTask(String type);
}
