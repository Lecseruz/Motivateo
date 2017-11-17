package com.example.magomed.motivateo.presenter;

import android.util.Log;

import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.Task;
import com.example.magomed.motivateo.net.utils.Constants;
import com.example.magomed.motivateo.service.TaskService;
import com.example.magomed.motivateo.service.UserService;
import com.example.magomed.motivateo.view.fragment.IListTaskFragmentView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListTodayTaskPresenterImpl implements IListTaskPresenter {
    private boolean isLoad = false;
    private IListTaskFragmentView view;

    @Override
    public void onCreate(IListTaskFragmentView view) {
        this.view = view;
    }

    @Override
    public void onResume() {
        loadTaskList();
    }

    @Override
    public void onItemClick(int position) {

    }

    private void loadTaskList(){
        if (!isLoad) {
//            TaskService service = ServiceFactory.createRetrofitService(TaskService.class, UserService.SERVICE_ENDPOINT);
//            Message message = view.getUserInformation();
//            service.getTask(message)
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<Message>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.d(Constants.ERROR_DATA, e.getMessage());
//                        }
//
//                        @Override
//                        public void onNext(Message response) {
//                            List<Task> list = (List<Task>) response.getBody();
//                            view.setListTaskAdapter(list);
//                            isLoad = true;
//                        }
//                    });
        }
    }
}
