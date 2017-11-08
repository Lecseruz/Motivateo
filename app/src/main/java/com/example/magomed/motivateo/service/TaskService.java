package com.example.magomed.motivateo.service;

import com.example.magomed.motivateo.models.Message;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by magomed on 07.11.17.
 */

public interface TaskService {
    @POST("/get")
    Observable<Message> getTask(@Body Message message);
}
