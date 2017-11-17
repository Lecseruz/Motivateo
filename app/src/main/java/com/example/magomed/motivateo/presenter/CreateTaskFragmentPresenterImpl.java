package com.example.magomed.motivateo.presenter;

import android.content.Context;
import android.util.Log;

import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.Task;
import com.example.magomed.motivateo.net.utils.Constants;
import com.example.magomed.motivateo.service.TaskService;
import com.example.magomed.motivateo.view.fragment.CreateTaskFragment;
import com.example.magomed.motivateo.view.fragment.ICreateFragmentTask;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateTaskFragmentPresenterImpl implements ICreateTaskFragmentPresenter {

    private boolean isLoad = false;

    private ICreateFragmentTask view;

    public CreateTaskFragmentPresenterImpl(Context context){
    }

    @Override
    public void onCreate(CreateTaskFragment view) {
        this.view = view;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void loadTaskList(){
        if (!isLoad) {
            List<Integer> list_count = new ArrayList<>();
            for (int i = 0; i < Constants.TASK_COUNT_REPEAT; ++i){
                list_count.add(i + 1);
            }
            List<Integer> list_type = new ArrayList<>();
            for (int i = 0; i < Constants.TASK_TYPE_REPEAT; ++i){
                list_type.add(i + 1);
            }
            view.setListTaskAdapter(list_count, list_type);
            isLoad = true;
        }
    }

    @Override
    public void sendTask(Task task) {
//        TaskService service = ServiceFactory.createRetrofitService(TaskService.class, Constants.SERVICE_ENDPOINT);
//        service.sendTask(task)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Message>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("Error", e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Message response) {
//                        if (response.getCode() == 200) {
//                            view.popFragmentFromStack();
//                        } else {
////                            signInFragment.errorSignIn();
//                        }
//                    }
//                });
    }
}
