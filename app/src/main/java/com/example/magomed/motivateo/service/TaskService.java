package com.example.magomed.motivateo.service;

import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.Task;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by magomed on 07.11.17.
 */

public interface TaskService {
    @POST("/get")
    Observable<Message> getTask(@Body Message message);

    @POST("/get")
    Observable<Message> sendTask(@Body Task Task);

}
