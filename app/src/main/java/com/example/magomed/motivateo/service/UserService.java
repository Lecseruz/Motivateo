package com.example.magomed.motivateo.service;


import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface UserService {
    @GET("/get")
    Call<ResponseBody> getUser();

    @POST("/signIn")
    Call<ResponseBody> signIn(@Body User user);

    @POST("/signUp")
    Call<ResponseBody> signUp(@Body User user);
}