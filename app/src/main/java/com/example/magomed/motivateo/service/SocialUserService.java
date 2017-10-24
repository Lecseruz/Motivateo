package com.example.magomed.motivateo.service;

import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.SocialUser;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface SocialUserService {
    @POST("/signInSocial")
    Observable<Message> signIn(@Body SocialUser socialUser);

    @POST("/signUpSocial")
    Observable<Message> signUp(@Body SocialUser socialUser);
}
