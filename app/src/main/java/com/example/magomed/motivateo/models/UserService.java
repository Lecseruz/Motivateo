package com.example.magomed.motivateo.models;


import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

public interface UserService {
    String SERVICE_ENDPOINT = "http://192.168.56.1:8080";

    @GET("/get")
    Observable<Message> getUser();

    @POST("/signIn")
    Observable<Message> signIn(@Body User user);

    @POST("/signUp")
    Observable<Message> signUp(@Body User user);
}