package com.example.magomed.motivateo.service;

import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.SocialUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface SocialUserService {
    @POST("/signInSocial")
    Call<ResponseBody> signIn(@Body SocialUser socialUser);

    @POST("/signUpSocial")
    Call<ResponseBody> signUp(@Body SocialUser socialUser);
}
